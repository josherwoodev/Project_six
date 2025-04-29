import { useEffect, useState } from 'react';
import axios from 'axios';

export default function App() {
  const [testText, setTestText] = useState('No data from backend');

  useEffect(() => {
    axios.get('http://localhost:8080/test', { headers: { 'Content-Type': 'application/json' } }).then(res => {
      setTestText(res.data[0]);
    }).catch(err => console.error(err));
  }, []);

  return (
    <>
      <h2>{testText}</h2>
    </>
  );
}