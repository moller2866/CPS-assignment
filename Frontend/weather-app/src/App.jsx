// App.js
import React from "react";

import { CssBaseline, ThemeProvider } from "@mui/material";
import { SnackbarProvider } from "notistack";
import Primary from "./views/PrimaryView";
import theme from "./theme";

function App() {
  return (
    <ThemeProvider theme={theme}>
      <CssBaseline />
      <SnackbarProvider maxSnack={3}>
        <Primary />
      </SnackbarProvider>
    </ThemeProvider>
  );
}

export default App;
