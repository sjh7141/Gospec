<template>
    <div>

        <!-- Services ================================================== -->
        <section id="nino-services">
            <div class="container">
                <h2 class="nino-sectionHeading">
                    Services
                </h2>

                <iframe width="560" height="315" src="https://www.youtube.com/embed/NPnNF8mqAk8" frameborder="0" allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>

                <div class="sectionContent">
                    <div class="fw row">
                        <div class="col-md-4 col-sm-6 item ">
                            <div layout="row">
                        
                                <i class="far fa-calendar-alt nino-icon fsr"></i>
                                <div>
                                    <h4 class="nino-serviceTitle">공모전 달력</h4>
                                    <p>국내 공모전 데이터를 달력 형식으로 한눈에 볼 수 있습니다. 
                                        또한 Gospec 회원은 관심 있는 공모전을 북마크 할 수 있는 기능을 제공합니다. 
                                        회원이 북마크한 공모전만 달력 형식으로 볼 수 있습니다. </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6 item ">
                            <div layout="row">
                                <i class="fas fa-comments nino-icon fsr"></i>
                                <div>
                                    <h4 class="nino-serviceTitle">공모전 팀 커뮤니티</h4>
                                    <p>공모전 별로 팀찾기, 팀원찾기 게시판을 통해 팀원, 팀을 찾을 수 있는 커뮤니티 서비스를 제공합니다.
                                        팀 찾기 게시판에서 글을 작성 한 뒤, 해당 글에 다른 유저가 팀 신청을 할 경우 수락/거절을 통해
                                        팀원을 구성할 수 있습니다.
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-6 item ">
                            <div layout="row">
                                <i class="fas fa-american-sign-language-interpreting nino-icon fsr"></i>
                                <div>
                                    <h4 class="nino-serviceTitle">공모전 팀매칭</h4>
                                    <p>GoSpec의 k-means clustering 알고리즘으로 Gospec 회원별로 공모전 팀원/ 팀을 매칭해줍니다.
                                        매칭된 회원의 프로필을 확인 기능과 쪽지 기능을 제공합니다.
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!--/#nino-services-->
    </div>
</template>

<script>
    export default {}
</script>

<style scoped>

/* =============== SECTION SERVICES =============== */
.nino-sectionHeading {
    font-family: 'NanumSquare';
	font-weight: 1000;
	font-size: 25px;
	line-height: 1;
	position: relative;
	padding-bottom: 20px;
	margin-bottom: 30px;
    margin-top: 40px;
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
#nino-services .nino-icon {
	font-size: 30px;
	margin-right: auto;
  margin-left: auto;
	color: #95e1d3;
}
#nino-services .item:not(:nth-last-child(-n+3)) {
	border-bottom: 1px solid #e5e5e5;
	margin-bottom: 50px;
	padding-bottom: 40px;
}
#nino-services .nino-serviceTitle {
	font-weight: 500;
	font-size: 14px;
}
.item {
  padding: 40px;
}
i {
  margin-right: 0;
}
</style>