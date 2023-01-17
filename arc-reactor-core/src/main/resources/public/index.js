$(document).ready(function(){
    $('.collapsible').collapsible();
    $("#applicationName").keypress(function(event) {
        if (event.which == 13) {
            fetchBuildInformation();
        }
    })
});
function fetchBuildInformation(){
    let applicationName = $("#applicationName").val().trim();
    if(applicationName!=""){
        // callBackend(applicationName)
        
        
    }
    $("#applicationName").val("");
}
function callBackend(applicationName){
    $.ajax({
        // url: '/application/info',
        url: 'https://jsonplaceholder.typicode.com/todos/1',
        type: "GET",
        contentType: "application/json; charset=utf-8",
       //  data: applicationInfo,
        cache: false,
        timeout: 50000,
        success: function(res){
            addToBuildContainer(applicationName,res['appkicationInfo']);
        },
        error: function(res){
            M.toast({html: 'I am a toast!', classes: 'rounded',inDuration: 3000});
        }
    });
}
function addToBuildContainer(applicationName,applicationInfo){
    let id =$.now();
    let html=$("#buildDetailsContainer").html();
    let tableContent = buildTable(applicationInfo)
    html=html+`<li id="${id}" class="singleApplication">
                    <div class="collapsible-header"><i class="material-icons">whatshot</i>${applicationName}</div>
                    <div class="collapsible-body">
                        <table class="responsive-table centered highlight">
                        <thead>
                      <tr>
                          <th>Environment</th>
                          <th>Pcf Url</th>
                          <th>Branch</th>
                          <th>Build Id</th>
                      </tr>
                    </thead>
                    <tbody>${tableContent}</tbody>
                  </table>
                    </div>
                </li>
            `;
    $("#buildDetailsContainer").html(html);
}
function buildTable(applicationInfo){
   var html='';
   let i=0
   while(i<7){
       html=html+`<tr>
                   <td>elit pellentesque</td>
                   <td>elementum nisi quis</td>
                   <td>diam in arcu cursus</td>
                   <td>rhoncus est pellentesque elit ullamcorper</td>
               </tr>`;
        i=i+1;
    }
   return html
}
