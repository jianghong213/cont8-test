<template>

  <div class="container-lg">
    
    <!-- image part -->
    <div class="row">
      <div class="col-lg-12" >
        <h1 style = "color: blue; font-size:35px;"> Welcome to CONT8 book store </h1>
        <img src="../../assets/img/home.jpg">
      </div>
    </div>

    <!-- about us part -->
    <div class="row">
      <div class ="col-lg-12" style ="height: auto; padding:1em;">
          <span style ="font-style: italic;">About us</span>
          <span style ="font-style: italic; text-align: center; white-space: pre-wrap; overflow-wrap: break-word;">
              We are a book store retail which used to import books from suppliers and sell them against customers. 
              A large number of customers has been utilizing our service since we launch new items fast in stock, plus our offered price is relatively lower than other competitors' offer.
              However, accomplished by the online shopping trend,  customers prefer purchasing books on the internet rather than visiting the physical stores.
              Hence, our company has launched a website for clients to make orders.
          </span>
        </div>
    </div>

    <!-- Feature part -->
    <div class="row">
      <div class ="col-lg-12" style ="height: auto; padding:1em;">
        <span style ="font-style: italic;"><h4>Our service features</h4></span>
      </div>
    </div>

    <div class="row">
      <div class ="col-lg-4" style ="height: auto; padding-top:0em; text-align: center;">
        <figure>
          <img src="../../assets/img/e-book.png" style ="width:170px;height:170px;">
          <figcaption><h3> Online book download </h3></figcaption>
        </figure>
          <span style ="font-style: italic; text-align: center; white-space: pre-wrap; ">
            Our customer can purchase the electronic version of the book and download them into their device
          </span>
      </div>

      <div class ="col-lg-4" style ="height: auto; padding-top:0em; text-align: center;">
        <figure>
          <img src="../../assets/img/db.png" style ="width:170px;height:170px;">
          <figcaption><h3> A tones of  books available</h3></figcaption>
        </figure>
          <span style ="font-style: italic; text-align: center; white-space: pre-wrap;">
            Our service provides more than 100000 e-books for customers. Customers can read and purchase them via our website
          </span>
      </div>


      <div class ="col-lg-4" style ="height: auto; padding-top:0em; text-align: center;">
        <figure>
          <img src="../../assets/img/preview.jpg" style ="width:170px;height:170px;">
          <figcaption><h3> Free preview of the books</h3></figcaption>
        </figure>
          <span style ="font-style: italic; text-align: center; white-space: pre-wrap;">
            Our company offers customer to view the first 30 page of books without any charges.
            Customer can choose whether to buy after the preview
          </span>
      </div>
    </div>

    


  </div>


  
</template>


<style>
.time {
  font-size: 13px;
  color: #999;
}
.bottom {
  margin-top: 13px;
  line-height: 12px;
}
.button {
  padding: 0;
  float: right;
}
.image {
  width: 100%;
  display: block;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
</style>

<script>
export default {
  data() {
    return {
      currentDate: new Date(),
    };
  },
};
</script>