console.log("Hello I am searching")

const search =()=>{

    let query=$("#search-input").val();
    if(query==''){
		$(".search-output").hide();
    }else{
        
        //let us get the api url where we can send the request
        let url=`http://localhost:8282/search/${query}`
        //now let us use fetch function to get the response and show it on our page
        fetch(url).then((response)=>{
            return response.json()
        }).then((data)=>{
            console.log(data)
            let text=`<div class='list-group'>`
            data.forEach((contact)=>{
                text+=`<a href='/user/contact/${contact.cid}' class='list-group-item list-group-item-action'>${contact.cname}</a>`
            })
            text+=`</div>`
            $(".search-output").html(text);
            $(".search-output").show();
        })
       
    }
}
