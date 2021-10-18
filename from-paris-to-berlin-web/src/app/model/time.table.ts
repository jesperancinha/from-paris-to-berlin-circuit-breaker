import {min} from "rxjs/operators";

export class TimeTable {
  constructor(name: string, minute: number, blockageType: string) {
    this.name = name;
    this.minute = minute;
    this.type = blockageType;
  }

  name: string | undefined;
  minute: number | undefined;
  type: string | undefined;
}
