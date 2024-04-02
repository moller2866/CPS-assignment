// App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Weather from './components/Weather';
import TemperatureChart from './components/TemperatureChart';
import Navigation from './components/Navigation'; // We'll create this next
import { createTheme, ThemeProvider } from '@mui/material/styles';
import './App.css';
import { SnackbarProvider, useSnackbar } from 'notistack';
import { Container } from '@mui/material';

const theme = createTheme({

  palette: {
    primary: {
      main: '#a1cb87',
    },
    secondary: {
      main: '#76a782',
    },
    background: {
      default: '#1B4242',
      paper: '#5C8374',
    },
    text: {
      primary: '#dceb88',
      secondary: '#dceb88',
    },
  },
});


function App() {
  return (
    <ThemeProvider theme={theme}>
      <SnackbarProvider maxSnack={3}>
        <Router>
          <Navigation />
          <Container maxWidth="sm">
            <Routes>
              <Route path="/temperature-chart" element={<TemperatureChart />} />
              <Route path="/" element={<Weather />} />
            </Routes>
          </Container>
        </Router>
      </SnackbarProvider>
    </ThemeProvider>
  );
}


export default App;
