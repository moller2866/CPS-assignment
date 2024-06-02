import { createTheme } from "@mui/material/styles";

const theme = createTheme({
  typography: {
    button: {
      textTransform: "none",
    },
  },
  components: {
    MuiCssBaseline: {
      styleOverrides: {
        body: {
          color: "white",
          backgroundColor: "#406767",
          "& h1": {
            color: "black",
          },
        },
      },
    },
    MuiBox: {
      styleOverrides: {
        root: {
          borderRadius: "30px",
        },
      },
    },
    MuiButton: {
      styleOverrides: {
        root: {
          borderRadius: "30px",
        },
      },
    },
    MuiTextField: {
      styleOverrides: {
        root: {
          borderRadius: "5px",
          marginTop: "5px",
          marginBottom: "5px",
        },
      },
      defaultProps: {
        variant: "filled",
      },
    },
  },
  palette: {
    primary: {
      main: "#558955",
    },
    secondary: {
      main: "#4C5873",
    },
    background: {
      default: "#406767",
      paper: "#5A7C7C",
    },
    text: {
      primary: "#dceb88",
      secondary: "#dceb88",
    },
  },
});

export default theme;
