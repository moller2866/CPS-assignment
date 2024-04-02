import React from 'react';
import { useNavigate } from 'react-router-dom';
import { Button, Box } from '@mui/material';

const Navigation = () => {
  const navigate = useNavigate();

  return (
    <Box sx={{ margin: 2 }}>
      <Button variant="contained" color="primary" onClick={() => navigate("/")}>
        Weather
      </Button>
      <Button variant="contained" color="secondary" sx={{ ml: 2 }} onClick={() => navigate("/temperature-chart")}>
        Temperature Chart
      </Button>
    </Box>
  );
};

export default Navigation;
