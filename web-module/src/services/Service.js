import request from "../helpers/api";

export const login = (loginData) => {
    return new Promise((resolve, reject) => {
        request('', 'POST', `user/login`, loginData).then((response) => {

            resolve(response);
        }).catch((error) => {
            reject(error);
        });
    });
}

export const getAvaiability = (token, location, from, to) => {
    return new Promise((resolve, reject) => {
        request(token, 'GET', `availability/${location}/${from}/${to}`).then((response) => {

            resolve(response);
        }).catch((error) => {
            reject(error);
        });
    });
}

export const pay = (token, card) => {
    return new Promise((resolve, reject) => {
        request(token, 'POST', `payment`, card).then((response) => {

            resolve(response);
        }).catch((error) => {
            reject(error);
        });
    });
}