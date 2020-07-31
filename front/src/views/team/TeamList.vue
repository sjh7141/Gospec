<template>
    <div>
        This is Contest Team List Page.
        <ul>
            <li><router-link :to="{ path: '/contest/' + $route.params.contest_id + '/teams/123' }">팀찾는게시물예제1 (게시물번호: 123)</router-link></li>
            <li><router-link :to="{ path: '/contest/' + $route.params.contest_id + '/teams/456' }">팀찾는게시물예제2 (게시물번호: 456)</router-link></li>
        </ul>
        <router-link :to="{ path: '/contest/' + $route.params.contest_id + '/teams/write' }">
            팀찾기 글 작성하기
        </router-link>
    </div>
</template>

<script>
export default {
    
}
</script>