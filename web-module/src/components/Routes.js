import React from "react";
import { Switch, Route } from "react-router-dom";
import Login from "./Login";
import Search from "./Search";

export function Routes() {

  return (
    <>
      <Route
        render={({ location }) => (
          <Switch location={location}>
            <Route exact path="/login" render={() => <Login />} />
            <Route exact path="/search" render={() => <Search />} />
            <Route component={Login} />
          </Switch>
        )}
      />
    </>
  );
}
