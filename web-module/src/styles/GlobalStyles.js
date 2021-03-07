import { createGlobalStyle } from "styled-components";

export const GlobalStyles = createGlobalStyle`
    * {
        border: 0;
        box-sizing: border-box;
        color: inherit;
        font-size: inherit;
        margin: 0;
        padding: 0;
    }

    html, body, #root {
        height: 100%;
    }

    body{
        background-color: #333;
        color: rgba(255,255,255,0.9);
        font-family: "Quicksand", -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif, "Apple Color Emoji", "Segoe UI Emoji", "Segoe UI Symbol";
        font-size: 18px;
    }

    a {
        font-weight: 500;
        text-decoration: none;
    }
`;
