import { render, screen } from '@testing-library/react';
import App from '../App.tsx';

describe('App', () => {
  it('should on initial render display hello world from the backend', () => {
    render(<App/>);

    expect(screen.getByText("Hello World")).toBeVisible();
  });
});