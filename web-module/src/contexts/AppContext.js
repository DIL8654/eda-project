import React, { createContext, useEffect, useRef, useState } from "react";

export const AppContext = createContext();

export function AppContextProvider({ children }) {

    const [authData, setAuth] = useState(null);
    const [bookNowOpen, setBookNowOpen] = useState(false);

    const [booking, setBooking] = useState({});
 

    // dispatcher
    
    return (
      <AppContext.Provider
        value={{
          authData,
          setAuth,
          bookNowOpen,
          setBookNowOpen,
          booking,
          setBooking,
        }}
      >
        {children}
      </AppContext.Provider>
    );
  }