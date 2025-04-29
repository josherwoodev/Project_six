import {render, screen} from "@testing-library/react";
import {describe, expect} from "vitest";
import CarPage from "../CarPage.tsx";
import * as CarService from "../CarService.ts";

describe('CarPage', () => {
    it('should display the name of the Car Dealership', () => {
        render(<CarPage/>)
        expect(screen.getByRole("heading", {name: "Howdy Honda"})).toBeVisible();
    });

    it('should call fetchCars from CarService', () => {
        const mockCarService = vi.spyOn(CarService, 'fetchCars').mockResolvedValue([]);
        render(<CarPage/>)
        expect(mockCarService).toHaveBeenCalledTimes(1);
    });

    it('should display a list of cars', () => {
        const car = {
            id:2,
            make: "Ford",
            model: "F250",
            year:2025,
            price:100,
            isUsed:false
        }
        vi.spyOn(CarService, 'fetchCars').mockResolvedValue([car]);
        render(<CarPage/>)
        expect(screen.findByText('2025 Ford F250')).toBeVisible()
        expect(screen.findByText('Price: 100')).toBeVisible()
        expect(screen.findByText('Used: false')).toBeVisible()


    });
})

