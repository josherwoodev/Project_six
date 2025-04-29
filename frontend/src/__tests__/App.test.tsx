import { render, screen, waitFor } from '@testing-library/react';
import App from '../App.tsx';

describe('App', () => {
  it('should on initial render display hello world from the backend', async () => {
    render(<App />);

    await waitFor(() => expect(screen.getByText('Hello World')).toBeVisible());
  });
});