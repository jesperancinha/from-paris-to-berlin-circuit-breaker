import {Component, OnInit} from '@angular/core';
import {Stomp} from "@stomp/stompjs";
import * as SockJS from "sockjs-client";

@Component({
  selector: 'app-fptb-overview',
  templateUrl: './fptb-overview.component.html',
  styleUrls: ['./fptb-overview.component.less']
})
export class FptbOverviewComponent implements OnInit {

  private stompClient;

  constructor() {
    const socket = new SockJS('/api/fptb/broker');
    this.stompClient = Stomp.over(socket);
    this.connect()
  }

  ngOnInit(): void {
    this.sendName()
  }

  title = 'grokonez';

  description = 'Angular-WebSocket Demo';
  greetings: string[] = [];
  disabled = true;
  name: string | undefined;

  setConnected(connected: boolean) {
    this.disabled = !connected;

    if (connected) {
      this.greetings = [];
    }
  }

  connect() {
    const _this = this;
    this.stompClient.connect({}, function (frame: any) {
      _this.setConnected(true);
      console.log('Connected: ' + frame);
      _this.sendName();
      _this.stompClient.subscribe('/topic/greetings', function (hello) {
        _this.showGreeting(JSON.parse(hello.body));
      });
      _this.stompClient.onreceive
    });
    this.sendName()
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

  showGreeting(message: any) {
    console.log(message)
    this.greetings.push(message);
  }
}
