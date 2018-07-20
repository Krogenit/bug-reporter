'use strict';

window.onload = initApp;

function initApp() {

    const node = {
        wrap: document.getElementById('wrap')
    }

    fetch('/rest/getall', {
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
            //let wrap = document.createElement('div');
            //wrap.id = joke.jokeText;
            //wrap.class = 'jokes';

            for (var i = 0; i < data.length; i++) {
                var joke = data[i];

                let item = document.createElement('div');
                item.className = 'joke_item';
                item.innerHTML = '<div class="joke_border" align="center">\n' +
                    '                    <p class="joke_text">'+joke.jokeText+'</p>\n' +
                    '                </div>\n' +
                    '                <p class="joke_category">Категория: '+joke.category+'</p>';
                node.wrap.appendChild(item);
            }
        })
        .catch(err => {
            console.warn(err.message);
        })
}