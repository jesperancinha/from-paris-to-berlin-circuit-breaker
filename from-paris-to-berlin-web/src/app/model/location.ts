import {RoadBlockageTime} from "./road.blockage.time";

export interface Location{
   id:  number;
   name: string;
   forward: Location[],
   blockageTimeTable: RoadBlockageTime[]
}
