import {Location} from "./location";

export interface Car {
  id: number,
  name: string,
  model: string,
  location: Location,
  formerLocations: Location[],
  secondsHold: number
}
