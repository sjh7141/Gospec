<template>
    <!-- Happy Client ================================================== -->
    <section id="nino-happyClient">
        <div class="container">
            <h2 class="nino-sectionHeading">
                <!-- <span class="nino-subHeading">Happy Clients</span> -->
                TEAM 깜냥
            </h2>
            <div class="sectionContent">
                <div class="row">
                    <div class="col-md-6 mx-auto">
                        <div class="item row align-items-center">
                            <div class='col-4'>
                                <img class='nino-avatar fsr img-circle' src="../../assets/images/ourteam/현수1.jpg" alt="">
                                <p class='mb-0 mt-3' style='font-size: 18px;'>김현수</p>
                                <p class='' style='color: gray'>Kim Hyun soo</p>
                                <div class='under-border mx-auto'></div>
                                <p class='role'>Back-end Developer</p>
                            </div>
                            <div class='col-8'>
                                <p>다양한 경험을 하고자 이것저것 건들여보는 
                                    <br>
                                    개발자입니다. 항상 재미있게 프로젝트에 
                                    <br>
                                    임하고 있습니다!</p>
                            </div>
                        </div>
                    </div>
                    <div class="w-100"></div>
                    <div class="col-md-6">
                        <div class="item d-flex row align-items-center">
                            <div class='col-4'>
                                <img class='nino-avatar fsr img-circle' src="../../assets/images/ourteam/호연.jpg" alt="">
                                <p class='mb-0 mt-3' style='font-size: 18px;'>김호연</p>
                                <p class='' style='color: gray'>Kim Ho Yeon</p>
                                <div class='under-border mx-auto'></div>
                                <p class='role'>Front-end Developer</p>
                            </div>
                            <div class='col-8'>
                                <p>세상의 소리에 귀기울이는 개발자 김호연입니다. 사람들이 원하고 필요로 하는 서비스에 귀기울임과 동시에 팀원들의 개발 효율성을 위해 귀기울이는 것이 저의 개발자로서의 비전입니다.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="item d-flex row align-items-center">
                            <div class='col-4'>
                                <img class='nino-avatar fsr img-circle' src="../../assets/images/ourteam/동규.jpg" alt="">
                                <p class='mb-0 mt-3' style='font-size: 18px;'>남동규</p>
                                <p class='' style='color: gray'>Nam Dong Gyu</p>
                                <div class='under-border mx-auto'></div>
                                <p class='role'>Front-end Developer</p>
                            </div>
                            <div class='col-8'>
                                <p>성장하기 위해 끊임없이 노력하는 개발자 남동규입니다. 더 많은 것을 경험하고 배워 더 뛰어난 개발자가 되는것이 목표입니다.</p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="item d-flex row align-items-center">
                            <div class='col-4'>
                                <img class='nino-avatar fsr img-circle' src="../../assets/images/ourteam/지훈.jpg" alt="">
                                <p class='mb-0 mt-3' style='font-size: 18px;'>서지훈</p>
                                <p class='' style='color: gray'>Seo Ji Hoon</p>
                                <div class='under-border mx-auto'></div>
                                <p class='role'>Back-end Developer</p>
                            </div>
                            <div class='col-8'>
                                <p>백엔드 개발자 서지훈입니다. <br>다양한 분야에서 불편하다고 느끼는 것은 꼭 해결해낼 수 있는 역량을 갖추고자 합니다. </p>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="item d-flex row align-items-center">
                            <div class='col-4'>
                                <img class='nino-avatar fsr img-circle' src="../../assets/images/ourteam/명주.jpg" alt="">
                                <p class='mb-0 mt-3' style='font-size: 18px;'>정명주</p>
                                <p class='' style='color: gray'>Jung Myung Ju</p>
                                <div class='under-border mx-auto'></div>
                                <p class='role'>Front-end Developer</p>
                            </div>
                            <div class='col-8'>
                                <p>호기심 많은 프로구글러입니다. <br> Es muss sein!</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
                    <!--/#nino-happyClient-->
</template>

<script>
  export default {}
</script>

<style scoped>
section {
	padding: 80px 0;
}
.nino-sectionHeading {
    font-family: 'NanumSquare';
	font-weight: 1000;
	font-size: 25px;
	line-height: 1;
	position: relative;
	padding-bottom: 20px;
	margin-bottom: 30px;
	text-align: center;
	color: #333;
}
.nino-sectionHeading:after {
	content: "";
	position: absolute;
	left: 50%;
	bottom: 0;
	width: 50px;
	height: 2px;
	background: #f38181;
	transform: translateX(-50%);
}
.nino-sectionHeading .nino-subHeading {
	display: block;
	font-weight: 400;
	font-family: 'Kaushan Script', cursive;
	font-size: 20px;
	margin-bottom: 20px;
}
.nino-sectionDesc {
	text-align: center;
	max-width: 750px;
	margin: 0 auto 40px;
}
#nino-happyClient .container {
	position: relative;
}
.nino-sectionHeading {
    margin-top: 40px;
    margin-bottom: 10px;
}
#nino-happyClient {
	background: url(../../assets/images/ourteam/bg.jpg) no-repeat center center / cover;
}
#nino-happyClient .item {
}
#nino-happyClient .nino-avatar {
	width: 120px;
}
#nino-happyClient .name {
	font-size: 14px;
}
#nino-happyClient .regency {
	position: relative;
	padding-bottom: 12px;
	display: inline-block;
	font-style: italic;
	margin-bottom: 12px;
}
#nino-happyClient .regency:after {
	content: "";
	position: absolute;
	left: 0;
	bottom: 0;
	width: 50px;
	height: 2px;
	background: #f38181;
}
.img-circle {
  border-radius: 50%;
}
img {
  max-width: 100%;
}
.desc {
  display: inline;
}
.name {
	font-size: 14px;
}
.regency {
	position: relative;
	padding-bottom: 12px;
	display: inline-block;
	font-style: italic;
	margin-bottom: 12px;
}
.nino-avatar {
    width: 70px;
}
.info {
  background-color: transparent;
  display: inline;
}
.fsr {
	-webkit-flex-shrink: 0;
	flex-shrink: 0;
}
.under-border {
    border-bottom: 2px solid;
    width: 100px;
    text-align: center;
    color: #f38181;;
}
.role {
    margin-top: 4px;
    font-style: italic;
}
</style>