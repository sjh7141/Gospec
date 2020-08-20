<template>
    <div>
        <!-- Map ================================================== -->
        <section id="nino-map">
            <div class="container">
                <h2 class="nino-sectionHeading" @click='openMap'>
                    <i class="mdi mdi-map-marker nino-icon">
                    </i>
                    <span class="text" v-if='show'>Open map</span>
                    <span class="text" v-else>Close map</span>
                </h2>
                <div class="mapWrap">
                    <iframe
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3165.2802945688472!2d127.03747101526791!3d37.501306779810236!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x357ca3ff67128961%3A0x55a56e8ffc5bc5d!2z66mA7Yuw7Lqg7Y287IqkIOyXreyCvA!5e0!3m2!1sko!2skr!4v1597147590953!5m2!1sko!2skr"
                        width="800"
                        height="600"
                        frameborder="0"
                        style="border:0"
                        allowfullscreen="allowfullscreen"></iframe>
                </div>
            </div>
        </section>
        <!--/#nino-map-->

        <!-- Footer ================================================== -->
        <footer id="footer">
            <div class="container">

            <div class="colInfo">
                <div class="footerLogo">
                    GoSpec
                </div>
                <h5 >스펙쌓기 필수 사이트 고스펙!</h5>
                <p>
                    
                    고스펙은 취업, 자기 개발을 위해 공모전에 참가를 원하는
                    <br>
                    사용자를 위해 맞춤 팀 매칭을 지원하는 웹 사이트로, 
                    <br>
                    최신 공모전, 인기 공모전 정보들을 다양한 
                    <br>
                    카테고리를 통해 확인할 수 있습니다.
                    
                </p>
                
                </div>
            </div>
            </footer>
                        <!--/#footer-->

                    </div>
</template>

<script > 
    export default {
    data() {
        return {
            show: true,
        }
    }
    , methods: {
        openMap() {
            if (this.show) {
                document.querySelector('#nino-map').classList.add('showMap')
            }
            else {
                document.querySelector('#nino-map').classList.remove('showMap')
            }
            this.show = !this.show
        },
        openFacebook() {window.open('http://facebook.com')},
        opentwitter() {window.open('http://twitter.com')},
        openinstagram() {window.open('http://instagram.com')},
        openpinterest() {window.open('http://pinterest.com')},
        opengoogle() {window.open('http://google.com')},
    }

}
</script>
<style>
@import 'https://fonts.googleapis.com/css?family=Montserrat:400,700';
@import 'https://fonts.googleapis.com/css?family=Roboto:300,400,500,700';
@import 'https://fonts.googleapis.com/css?family=Kaushan+Script';

#nino-map {

    padding: 40px 0;
    /* background: url(../../assets/images/index/bg-map.jpg) no-repeat center center / cover; */
    background: #f8f8f8;
}
#nino-map .nino-sectionHeading {
    color: #f28989;
    margin: 0;
    cursor: pointer;
}
#nino-map .nino-sectionHeading .nino-icon {
    display: block;
    margin-bottom: 10px;
    font-size: 40px;
}
#nino-map .mapWrap {
    visibility: hidden;
    opacity: 0;
    height: 0 !important;
    margin-top: 20px;
}
#nino-map .mapWrap iframe {
    width: 100%;
    height: 300px;
}
#nino-map.showMap .mapWrap {
    visibility: visible;
    opacity: 1;
    height: auto !important;
    border: 2px solid #ff9c00;
}
#nino-map .nino-locationInfo {
    width: 250px;
}
/* =============== LAYOUT - FOOTER AREA =============== */
footer {
    padding-top: 50px;
    /* background: #f8f8f8; */
    background: url(../../assets/images/index/bg-map.jpg) no-repeat center center / cover;
}
footer .nino-colHeading {
    margin: 0 0 25px;
    font-size: 14px;
}
footer .colInfo {
    margin-bottom: 30px;
}
/* ----------- COL 1 ----------- */
footer .footerLogo {
    color: #c0c0c0;
    font-size: 40px;
    font-family: 'Montserrat', sans-serif;
    font-weight: 700;
    display: inline-block;
    margin-bottom: 20px;
    line-height: 100%;
}
footer .nino-followUs {
    margin: 25px 0;
}
footer .nino-followUs .totalFollow {
    color: #333;
    font-weight: 500;
    font-family: 'Montserrat', sans-serif;
    border-bottom: 1px solid #e5e5e5;
    padding-bottom: 5px;
    margin-bottom: 10px;
}
footer .nino-followUs .totalFollow span {
    font-size: 18px;
    font-weight: 700;
}
footer .nino-followUs .socialNetwork .text {
    font-style: italic;
    font-weight: 300;
    display: inline-block;
    margin-right: 10px;
}
footer .nino-followUs .socialNetwork .nino-icon {
    display: inline-block;
    margin: 0 3px;
    font-size: 40px;
    color: #95e1d3;
}
footer .nino-subscribeForm .input-group .btn,
footer .nino-subscribeForm .input-group input {
    border-radius: 0;
}
footer .nino-subscribeForm .input-group input {
    border-color: #e7e7e7;
    box-shadow: none;
    font-style: italic;
    font-size: 15px;
}
footer .nino-subscribeForm .input-group .btn {
    font-weight: 700;
    font-family: 'Montserrat', sans-serif;
    font-size: 14px;
    background: #95e1d3;
    border: none;
}
/* ----------- COPYRIGHT ----------- */
footer .nino-copyright {
    text-align: center;
    border-top: 1px solid #e5e5e5;
    padding: 20px 0;
}
/* =============== SEARCH FORM =============== */
#nino-searchForm {
    position: fixed;
    left: 0;
    top: -100%;
    width: 100%;
    height: 100%;
    background: rgba(0,0,0,.9);
    padding: 20px;
    z-index: 10;
    opacity: 1;
    -webkit-transition: all 0.3s ease-in-out;
    -moz-transition: all 0.3s ease-in-out;
    -o-transition: all 0.3s ease-in-out;
    -ms-transition: all 0.3s ease-in-out;
    transition: all 0.3s ease-in-out;
}
#nino-searchForm.open {
    top: 0;
    opacity: 1;
}
#nino-searchForm .nino-searchInput {
    position: relative;
    top: 50%;
    height: 100px;
    box-shadow: none;
    font-size: 70px;
    background: none;
    border: none;
    border-bottom: 1px solid #333;
    font-weight: 700;
    padding: 20px 0;
    color: #fff;
    -webkit-transform: translateY(-50%);
    -moz-transform: translateY(-50%);
    -o-transform: translateY(-50%);
    -ms-transform: translateY(-50%);
    transform: translateY(-50%);
}
#nino-searchForm .nino-close {
    position: absolute;
    top: 20px;
    right: 20px;
    font-size: 60px;
    cursor: pointer;
}
#nino-searchForm .nino-close:hover {
    color: #fff;
}
</style>