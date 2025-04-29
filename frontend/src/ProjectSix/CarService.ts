import {Car} from "../CarType";

export function fetchCars():Promise<Car[]>{
    const mockedCars: Car[]=[
        {
            id:1,
            make: "Bugati",
            model: "Subabru",
            year:1832,
            price:93927,
            isUsed:false
        },
        {
          id: 2,
          make: "Honba",
            model: "Bivic",
            year: 2001,
            price: 1,
            isUsed: true
        }
        ]
    return Promise.resolve(mockedCars);
}
