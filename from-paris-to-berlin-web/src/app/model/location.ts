import {RoadBlockageTime} from "./road.blockage.time";

export interface Location{
   id:  number;
   name: String;
   forward: Location[],
   blockageTimeTable: RoadBlockageTime[]
}
