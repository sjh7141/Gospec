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
                <div class="row">
                    <div class="col-md-4">
                        <div class="colInfo">
                            <div class="footerLogo">
                                <a href="#">GoSpec</a>
                            </div>
                            <p>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                                incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                                nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                            </p>
                            <div class="nino-followUs">
                                <div class="totalFollow">
                                    <span>15k</span>
                                    followers</div>
                                <div class="socialNetwork">
                                    <span class="text">Follow Us:
                                    </span>
                                    <a href="" class="nino-icon">
                                        <i class="mdi mdi-facebook"></i>
                                    </a>
                                    <a href="" class="nino-icon">
                                        <i class="mdi mdi-twitter"></i>
                                    </a>
                                    <a href="" class="nino-icon">
                                        <i class="mdi mdi-instagram"></i>
                                    </a>
                                    <a href="" class="nino-icon">
                                        <i class="mdi mdi-pinterest"></i>
                                    </a>
                                    <a href="" class="nino-icon">
                                        <i class="mdi mdi-google-plus"></i>
                                    </a>
                                    <a href="" class="nino-icon">
                                        <i class="mdi mdi-youtube-play"></i>
                                    </a>
                                    <a href="" class="nino-icon">
                                        <i class="mdi mdi-dribbble"></i>
                                    </a>
                                    <a href="" class="nino-icon">
                                        <i class="mdi mdi-tumblr"></i>
                                    </a>
                                </div>
                            </div>
                            <form action="" class="nino-subscribeForm">
                                <div class="input-group input-group-lg">
                                    <input
                                        type="email"
                                        class="form-control"
                                        placeholder="Your Email"
                                        required="required">
                                        <span class="input-group-btn">
                                            <button class="btn btn-success" type="submit">Subscribe</button>
                                        </span>
                                    </div>
                                </form>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6">
                            <div class="colInfo">
                                <h3 class="nino-colHeading">Blogs</h3>
                                <ul class="listArticles">
                                    <li layout="row" class="verticalCenter">
                                        <a class="articleThumb fsr" href="#">
                                            <img src="images/our-blog/img-4.jpg" alt=""></a>
                                            <div class="info">
                                                <h3 class="articleTitle">
                                                    <a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing</a>
                                                </h3>
                                                <div class="date">Jan 9, 2016</div>
                                            </div>
                                        </li>
                                        <li layout="row" class="verticalCenter">
                                            <a class="articleThumb fsr" href="#">
                                                <img src="images/our-blog/img-5.jpg" alt=""></a>
                                                <div class="info">
                                                    <h3 class="articleTitle">
                                                        <a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing</a>
                                                    </h3>
                                                    <div class="date">Jan 9, 2016</div>
                                                </div>
                                            </li>
                                            <li layout="row" class="verticalCenter">
                                                <a class="articleThumb fsr" href="#">
                                                    <img src="images/our-blog/img-6.jpg" alt=""></a>
                                                    <div class="info">
                                                        <h3 class="articleTitle">
                                                            <a href="#">Lorem ipsum dolor sit amet, consectetur adipiscing</a>
                                                        </h3>
                                                        <div class="date">Jan 9, 2016</div>
                                                    </div>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="col-md-4 col-sm-6">
                                        <div class="colInfo">
                                            <h3 class="nino-colHeading">instagram</h3>
                                            <div class="instagramImages clearfix">
                                                <a href="#">
                                                    <img src="images/instagram/img-1.jpg" alt=""></a>
                                                    <a href="#">
                                                        <img src="images/instagram/img-2.jpg" alt=""></a>
                                                        <a href="#">
                                                            <img src="images/instagram/img-3.jpg" alt=""></a>
                                                            <a href="#">
                                                                <img src="images/instagram/img-4.jpg" alt=""></a>
                                                                <a href="#">
                                                                    <img src="images/instagram/img-5.jpg" alt=""></a>
                                                                    <a href="#">
                                                                        <img src="images/instagram/img-6.jpg" alt=""></a>
                                                                        <a href="#">
                                                                            <img src="images/instagram/img-7.jpg" alt=""></a>
                                                                            <a href="#">
                                                                                <img src="images/instagram/img-8.jpg" alt=""></a>
                                                                                <a href="#">
                                                                                    <img src="images/instagram/img-9.jpg" alt=""></a>
                                                                                    <a href="#">
                                                                                        <img src="images/instagram/img-3.jpg" alt=""></a>
                                                                                        <a href="#">
                                                                                            <img src="images/instagram/img-4.jpg" alt=""></a>
                                                                                            <a href="#">
                                                                                                <img src="images/instagram/img-5.jpg" alt=""></a>
                                                                                            </div>
                                                                                            <a href="#" class="morePhoto">View more photos</a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="nino-copyright">Copyright &copy; 2020
                                                                                    <a
                                                                                        target="_blank"
                                                                                        href="#"
                                                                                        title="Ninodezign.com - Top quality open source resources for web developer and web designer">Gospec.com</a>
                                                                                        . All Rights Reserved.
                                                                                    <br/>
                                                                                    GoSpec template by
                                                                                    <a href="#">깜냥</a>
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
        }
    }

}
</script>
<style>
@import 'https://fonts.googleapis.com/css?family=Montserrat:400,700';
@import 'https://fonts.googleapis.com/css?family=Roboto:300,400,500,700';
@import 'https://fonts.googleapis.com/css?family=Kaushan+Script';

#nino-map {

    padding: 40px 0;
    background: url(../../assets/images/index/bg-map.jpg) no-repeat center center / cover;
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
    background: #f8f8f8;
}
footer .nino-colHeading {
    margin: 0 0 25px;
    font-size: 14px;
}
footer .colInfo {
    margin-bottom: 30px;
}
/* ----------- COL 1 ----------- */
footer .footerLogo a {
    color: #ccc;
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
    font-size: 18px;
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
    text-transform: uppercase;
    font-weight: 700;
    font-family: 'Montserrat', sans-serif;
    font-size: 14px;
    background: #95e1d3;
    border: none;
}
/* ----------- COL 2 ----------- */
footer .listArticles > li {
    margin-bottom: 20px;
}
footer .listArticles > li:last-child {
    margin-bottom: 0;
}
footer .listArticles .articleThumb {
    width: 120px;
    margin-right: 15px;
}
footer .listArticles .articleTitle {
    font-size: 12px;
    margin: 0 0 5px;
    line-height: 1.4;
}
footer .listArticles .date {
    font-style: italic;
}
/* ----------- COL 3 ----------- */
footer .instagramImages a {
    float: left;
    width: 82px;
    margin: 1px;
}
footer .morePhoto {
    color: #999;
    font-style: italic;
    margin-top: 10px;
    display: inline-block;
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