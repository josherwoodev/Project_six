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
})

