import {Component, OnInit, ViewChild} from '@angular/core';
import {Stomp} from "@stomp/stompjs";
import * as SockJS from "sockjs-client";
import {RoadRace} from "../model/road.race";
import {DiagramComponent} from "gojs-angular";
import * as go from 'gojs';
import {Location} from "../model/location";

@Component({
  selector: 'app-fptb-overview',
  templateUrl: './fptb-overview.component.html',
  styleUrls: ['./fptb-overview.component.less']
})
export class FptbOverviewComponent implements OnInit {

  private stompClient;


  @ViewChild('myDiag', {static: false}) public myDiag: DiagramComponent | undefined;

  @ViewChild(DiagramComponent, {static: false}) public diagramComponent: DiagramComponent | undefined;

  constructor() {
    const socket = new SockJS('/api/fptb/broker');
    this.stompClient = Stomp.over(socket);
  }

  ngOnInit(): void {
    this.initDiagram()
    this.connect()
  }

  title = 'grokonez';

  description = 'Angular-WebSocket Demo';
  roadRace: RoadRace | undefined;
  disabled = true;
  name: string | undefined;

  setConnected(connected: boolean) {
    this.disabled = !connected;
  }

  connect() {
    const _this = this;
    this.stompClient.connect({}, function (frame: any) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);
      _this.sendName();
      _this.stompClient.subscribe('/topic/greetings', function (hello) {
        _this.processRoadRace(JSON.parse(hello.body) as RoadRace);
      });
    });
    // this.sendName()
  }

  disconnect() {
    if (this.stompClient != null) {
      this.stompClient.disconnect();
    }

    this.setConnected(false);
    console.log('Disconnected!');
  }

  sendName() {
    this.stompClient.send(
      '/app/hello',
      {},
      JSON.stringify({'name': this.name})
    );
  }

  processRoadRace(roadRace: RoadRace) {
    this.roadRace = roadRace;
    let nodes: any[] = [];
    let links: any[] = [];
    let location = roadRace.paris;
    this.addNodesRecursively(nodes, links, location);
    this.dia?.model.startTransaction("changing data");
    this.nodeDataArray = Array.from(new Set(nodes.map(t => JSON.stringify(t)))).map(t => JSON.parse(t));
    this.linkDataArray = Array.from(new Set(links.map(t => JSON.stringify(t)))).map(t => JSON.parse(t));
    this.dia?.model.commitTransaction("changed data");
    this.roadRace = roadRace;
  }

  private addNodesRecursively(nodes: any[], links: any[], location: Location) {
    nodes.push(FptbOverviewComponent.toNodeLocation(location))
    location.forward.forEach(subLocation => {
      links.push({
        from: location.id,
        to: subLocation.id
      })
      this.addNodesRecursively(nodes, links, subLocation);
    })
  }

  private static toNodeLocation(location: Location) {
    let status = location.blockageTimeTable.filter(t => t.minute == new Date().getMinutes()).length == 0 ? "FREE" : "BLOCK";
    return {
      key: location.id,
      text: location.name,
      color: "green",
      status: status
    };
  }

  public nodeDataArray = [
    {key: 1, text: "Wait a moment, your map is being generated...", color: "lightblue", status: "BLOCK"},
    {key: 2, text: "Wait a moment, your map is being generated...", color: "orange"},
    {key: 3, text: "Wait a moment, your map is being generated...", color: "orange"}

  ]

  public linkDataArray = [
    {from: 1, to: 2},
    {from: 1, to: 3},
  ]

  public diagramDivClassName = 'myDiagramDiv';
  public diagramModelData = {prop: 'value', color: 'red'};

  public dia: go.Diagram | undefined;
  displayedColumns = ["id", "name", "model", "location", "progress"];

  initDiagram(): go.Diagram {
    const $ = go.GraphObject.make;
    const dia = $(go.Diagram, {
      'toolManager.hoverDelay': 100,  // 100 milliseconds instead of the default 850
      allowCopy: false,
      layout:
        $(go.LayeredDigraphLayout)
    });

    const bluegrad = '#FF0000';
    const pinkgrad = '#00FF00';

    dia.add(
      $(go.Part, 'Table', {position: new go.Point(600, 10), selectable: false},
        $(go.TextBlock, 'Key', {row: 0, font: '700 14px Droid Serif, sans-serif'}),
        $(go.Panel, 'Horizontal', {row: 1, alignment: go.Spot.Left},
          $(go.Shape, 'Rectangle', {desiredSize: new go.Size(30, 30), fill: bluegrad, margin: 5}),
          $(go.TextBlock, 'Blocked', {font: '700 13px Droid Serif, sans-serif'})),
        $(go.Panel, 'Horizontal', {row: 2, alignment: go.Spot.Left},
          $(go.Shape, 'Rectangle', {desiredSize: new go.Size(30, 30), fill: pinkgrad, margin: 5}),
          $(go.TextBlock, 'Free', {font: '700 13px Droid Serif, sans-serif'})
        )
      )
    );

    dia.nodeTemplate = $(go.Node, 'Auto', {
        deletable: false
      }, new go.Binding('text', 'name'),
      $(go.Shape, 'Rectangle', {
        fill: 'lightgray',
        stroke: null,
        strokeWidth: 0,
        stretch: go.GraphObject.Fill,
        alignment: go.Spot.Center
      }, new go.Binding('fill', 'status', (status) => {
        if (status === 'BLOCK') {
          return bluegrad;
        }
        if (status === 'FREE') {
          return pinkgrad;
        }
        return 'orange';
      })),
      $(go.TextBlock, {
        font: '700 12px Droid Serif, sans-serif',
        textAlign: 'center',
        margin: 10, maxSize: new go.Size(80, NaN)
      }, new go.Binding('text', 'text'))
    );

    dia.linkTemplate = $(go.Link, {
      routing: go.Link.Bezier,
      corner: 5,
      selectable: false
    }, $(go.Shape, {
      strokeWidth: 4,
      stroke: 'blue'
    }));

    let graphLinksModel = new go.GraphLinksModel(this.nodeDataArray,
      this.linkDataArray);
    graphLinksModel.linkKeyProperty = "id"
    dia.model = graphLinksModel;

    this.dia = dia;

    return dia;
  }


  onModelChange(event: any) {
    console.log('Event: ', event);
  }

  getFormerLocationText(formerLocations: Location[]): string {
    return formerLocations.map(t=>t.name).join(" - 🚙 - ") + " 🚙"
  }
}
