import request from "../helpers/api";

export const login = (loginData) => {
    const BASE_URL = process.env.REACT_APP_API_AUTH;

    return new Promise((resolve, reject) => {
        request('', BASE_URL, 'POST', `authentication/oauth/token`, loginData).then((response) => {

            resolve(response);
        }).catch((error) => {
            reject(error);
        });
    });
}

export const getAllLocations = (token) => {
    const BASE_URL = process.env.REACT_APP_API_SEARCH;

    return new Promise((resolve, reject) => {
        request(token, BASE_URL, 'GET', `search/allLocations`).then((response) => {
            resolve(response);
        }).catch((error) => {
            reject(error);
        });
    });
}

export const getAvaiability = (token, location, from, to) => {
    const BASE_URL = process.env.REACT_APP_API_SEARCH;

    return new Promise((resolve, reject) => {
        request(token, BASE_URL, 'POST', `availability/search/search/range`, {
            location: location,
            from: Date.parse(from),
            to: Date.parse(to),
        }).then((response) => {

            resolve(response);
        }).catch((error) => {
            reject(error);
        });
    });
}

export const createBooking = (token, req) => {
    const BASE_URL = process.env.REACT_APP_API_URL;

    return new Promise((resolve, reject) => {
        request(token, BASE_URL, 'POST', `search/reservation/create`, req).then((response) => {

            resolve(response);
        }).catch((error) => {
            reject(error);
        });
    });
}

export const pay = (token, card) => {
    const BASE_URL = process.env.REACT_APP_API_URL;

    return new Promise((resolve, reject) => {
        request(token, BASE_URL, 'POST', `payment`, card).then((response) => {

            resolve(response);
        }).catch((error) => {
            reject(error);
        });
    });
}