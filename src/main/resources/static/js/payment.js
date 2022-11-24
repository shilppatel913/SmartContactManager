console.log("Payment script working")

//when the pay now button is clicked the below function will sent request to the server to generate the order payment

const generateOrder=()=>{
    console.log("Pay Now button clicked");
    let amount=$("#payment_amt").val();
    if(amount==null || amount==''){
        alert("Amount cannot be null");
        return;
    }
        //when the amount is entered now send a request to the server using ajax and get the order_id  and other things as response

    $.ajax(
        { 
            url:'/user/create_order',
            data:JSON.stringify({amount:amount,info:'order_request'}),
            contentType: 'application/json',
            type: 'POST',
            dataType: 'json',
            success:function(response){
                console.log(response);
                if(response.status=="created"){
                    let options={
                        key:"rzp_test_7li0u6u7gg0jLt",
                        amount:response.amount,
                        currency:"INR",
                        name:"Smart Contact Manager",
                        description:"Donation",
                        image:"https://learncodewithdurgesh.com/images/logo.png",
                        order_id:response.id,
                        handler:function(response){
                            console.log(response.razorpay_payment_id);
                            console.log(response.razorpay_order_id);
                            console.log(response.log(razorpay_signature));
                            console.log("Payment successful");
                        },
                        prefill:{
                            name:"",
                            email:"",
                            contact:""
                        },
                        notes:{
                            address:"Shilp Patel"
                        },
                        theme:{
                            color:"#3399cc"
                        }
                    }
                    
                    let rzp=new Razorpay(options);
                    rzp.on("payment.failed",function(response){
                        console.log(response.error.code);
                        console.log("Oops payment failed")
                    })
                    rzp.open();



                }
            },
            error:function(error){
                console.log(error)
            }
        }
    )
}