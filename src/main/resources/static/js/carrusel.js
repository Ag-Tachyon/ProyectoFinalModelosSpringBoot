const slider = document.querySelector('.slider');
const images = document.querySelectorAll('.carrusel__img');

let indice = 0;
let size = images[0].clientWidth;
const intervalTime = 3000;

function nextSlide(){
    if(indice >= images.length - 1){
        indice = 0;
    } else{
        indice++;
    }

    slider.style.transform = 'translateX(' + (-size * indice) + 'px)';
}

let slideInterval = setInterval(nextSlide, intervalTime);
