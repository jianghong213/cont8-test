<template>
  <div class="login_component">
    <h1>{{ msg }}</h1>
    
    <form>
      <div class="mb-3">
        <label for="user_id">User ID:</label><br>
        <input type="text" class="form-control form-control-sm" id="user_id" name="user_id" value=""><br>
      </div>

      <div class="mb-3">
        <label for="password">Last name:</label><br>
        <input type="text" class="form-control form-control-sm" id="password" name="password" value=""><br><br>
      </div>

      <button type="submit" class="btn btn-primary" @click="login">Login</button>
    </form> 
    
  </div>
</template>

<script>
export default {
  name: 'LoginComponent',
  props: {
    msg: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
