<template>
  <div>{{this.$route.query.name}}</div>
</template>

<script>
export default {
  name: 'face',
  data(){
    return{

    }
  },
  mounted() {
    console.log(this.$route.query)
  }
}
</script>

<style scoped>

</style>
