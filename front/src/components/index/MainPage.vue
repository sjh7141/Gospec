<template>
  <div id='body'>
    <div>
      <img src="../../assets/images/index/bg.jpg" id='bg'>
      <img src="../../assets/images/index/moon.png" id='moon' style='margin-top: 80px;'>
      <img src="../../assets/images/index/mountain.png" id='mountain'>
      <img src="../../assets/images/index/road.png" id='road'>
      <MainPageInterest id='text' />
      <div class="main_logo">
      <h2 id='gospec'>
      <ul>
      <li>
        GOSPEC
      </li>
        <span style="font-size:35px;"> 
      <li class="image blinking">
          <i class=" fas fa-angle-double-down"></i>
      </li>
      <li class="image blinking">scroll</li>
      </span>
      </ul>
      </h2>
      </div>
    </div>
  </div>
</template>

<script>
import MainPageInterest from './MainPageInterest.vue'
export default {
  components: {
    MainPageInterest
  },
  data() {
    return {
    }
  },
  methods: {
    start() {
      let bg = document.getElementById("bg")
      let moon = document.getElementById("moon")
      let mountain = document.getElementById("mountain")
      let road = document.getElementById("road")
      let text = document.getElementById("text")
      let gospec = document.getElementById('gospec')
      window.addEventListener('scroll', function() {
        var value = window.scrollY;

        bg.style.top = value * 1 + 'px'
        moon.style.left = -value * 0.15 + 'px'
        moon.style.top = value * 0.7 + 'px'
        mountain.style.top = value * 1.1 + 'px'
        road.style.top = value * 1.5 + 'px'
        text.style.top = -value * 1 + 'px'
        gospec.style.top = value * 1.6 + 'px'
      })
    },
  },
  mounted() {
    this.start()
  },
}
</script>

<style scoped>
* {
  margin: 0;
  padding: 0;
}
#body {
  /* background: gray; */
  height: 1500px;
}
#body div {
  position: relative;
  width: 100%;
  height: 180vh;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
}
#body div:before{
  content: '';
  position: absolute;
  /* bottom: 0; */
  width: 100%;
  background: linear-gradient(to top, gray, transparent);
  z-index: 3;
}

#body div img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  object-fit: cover;
  pointer-events: none;
}
#body #text
{
  margin-top: 1000px;
  position: absolute;
  color: #fff;
  font-size: 10em;
  z-index: 1;
}
#body #road {
  margin-top: 70px;
  z-index: 2;
}
#body #gospec {
  top: 0;
  margin-top: 280px;
  position: absolute;
  color: #fff;
  font-size: 8em;
  z-index: 0;
  font-family: 'NanumSquare';
  font-weight: 1000;
}
#body #mountain {
  margin-top: 100px;
  z-index: 1
}
#body #moon {
  top: 20;
}
.main_logo{
  display: inline;
  vertical-align: middle;

}
ul {
  list-style:none;
}
.blinking{
	-webkit-animation:blink 1.5s ease-in-out infinite alternate;
    -moz-animation:blink 1s ease-in-out infinite alternate;
    animation:blink 1s ease-in-out infinite alternate;
}
@-webkit-keyframes blink{
    0% {opacity:0;}
    100% {opacity:1;}
}
@-moz-keyframes blink{
    0% {opacity:0;}
    100% {opacity:1;}
}
@keyframes blink{
    0% {opacity:0;}
    100% {opacity:1;}
}
</style>