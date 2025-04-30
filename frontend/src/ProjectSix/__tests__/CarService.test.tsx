import {setupServer} from "msw/node";
import {http, HttpResponse} from "msw";
import axios from "axios";
import {afterAll, afterEach, beforeAll, describe, expect, it} from "vitest";
import {Car} from "../../CarType";
import {fetchCars} from "../CarService";

describe('CarService', () => {
    axios.defaults.baseURL = "http://localhost:3000"

    const server = setupServer()
    beforeAll(() => server.listen({onUnhandledRequest: 'error'}))
    afterAll(() => server.close())
    afterEach(() => server.resetHandlers())

    it('should send a get request to fetch existing cars', async () => {
        const expectedCarList: Car[] = [
            {id: 1, make: "Ford", model:"Mustang", year:2000, price: 20000.88, used:true},
            {id:2, make:"Nissan", model:"Rogue", year: 1999, price: 9.33, used:false}
        ];

        server.use(http.get('/api/cars', () =>
            HttpResponse.json(expectedCarList, {status: 200})
        ))

        expect(await fetchCars()).toStrictEqual(expectedCarList);
    });
});