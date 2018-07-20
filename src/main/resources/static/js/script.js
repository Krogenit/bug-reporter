'use strict';

window.onload = initApp;

function initApp() {

    const node = {
        likeButton: document.getElementById("like_button"),
        dislikeButton: document.getElementById("dislike_button"),
        out: document.getElementById("out")
    }

    fetch('/rest/joke', {
        method: 'get'
    })
        .then(response => {

            if(response.ok) {
                return response.json();
            } else {
                throw new Error('ой мама ничего не могу двигать паралевилизировали ой не могууууу');
            }
        })
        .then(data => {
            node.out.innerHTML = data.jokeText;
            store.id = data.id;
        })
        .catch(err => {
            console.warn(err.message);
        })

    const store = {
        id: null
    }

    node.likeButton.onclick = function () {
        fetch('rest/add', {
            method: 'post',
            body: store.id
        })
            .then(response => {

            if(response.ok) {
                store.id = null;
                fetch('/rest/joke', {
                    method: 'get'
                })
                    .then(response => {

                        if (response.ok) {
                            return response.json();
                        } else {
                            throw new Error('ой мама ничего не могу двигать паралевилизировали ой не могууууу');
                        }
                    })
                    .then(data => {
                        node.out.innerHTML = data.jokeText;
                        store.id = data.id;
                    })
                    .catch(err => {
                        console.warn(err.message);
                    })
            } else {
                throw new Error('ой мама ничего не могу двигать паралевилизировали ой не могууууу');
            }
        })
            .catch(err => {
                console.warn(err.message);
            })
    }
    
    node.dislikeButton.onclick = function () {
        fetch('/rest/joke', {
            method: 'get'
        })
            .then(response => {

                if(response.ok) {
                    return response.json();
                } else {
                    throw new Error('ой мама ничего не могу двигать паралевилизировали ой не могууууу');
                }
            })
            .then(data => {
                node.out.innerHTML = data.jokeText;
                store.id = data.id;
            })
            .catch(err => {
                console.warn(err.message);
            })
    }
}