import {Car} from "../CarType";
import axios from "axios";

export function fetchCars():Promise<Car[]>{
    return axios.get("/api/cars").then((r)=>r.data);
}
