export {getFetch, getAcceptJsonFetch, putFetch, postFetch}

function getFetch(url) {
    return fetch(`.${url}`).then(data => {
        if (data.status === 400) {
            exceptionHandling(data.json());
        } else if (!data.ok) {
            throw new Error(data.status);
        }
        return data.json()
    });
}

function getAcceptJsonFetch(url) {
    return fetch(`.${url}`, {
        method: "get",
        headers: {
            accept: "application/json"
        }
    }).then(data => {
        if (data.status === 400) {
            exceptionHandling(data.json());
        } else if (!data.ok) {
            throw new Error(data.status);
        }
        return data.json()
    });
}

function postFetch(url, body = {}) {
    return fetch(`.${url}`, {
        method: "post",
        body: JSON.stringify(body),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(data => {
        if (data.status === 400) {
            exceptionHandling(data.json());
        } else if (!data.ok) {
            throw new Error(data.status);
        }
    })
}

function putFetch(url, body = {}) {
    return fetch(`.${url}`, {
        method: "put",
        body: JSON.stringify(body),
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(data => {
        if (data.status === 400) {
            exceptionHandling(data.json());
        } else if (!data.ok) {
            throw new Error(data.status);
        }
        return data.json()
    })
}

function exceptionHandling(errorPromise) {
    errorPromise.then(data => {
        alert(data.errorMsg);
    })
}