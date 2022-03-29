
function closeDiv()
{
	document.getElementById("itemMusic").style.display = 'none';
	document.getElementById("listLoadingItemMusic").style.display = 'none';
	youtubeMusic.value='';
}

function loadYoutubeMusic()
{
	document.getElementById("itemMusic").style.display = 'none';
	document.getElementById("listLoadingItemMusic").style.display = 'block';
	
	var temp="";
	let data = {'id':null,
				'nextPageToken':null,
				'movieId':null,
				'movieTitle':null,
				'channelId':null,
				'channelTitle':null,
				'url': youtubeMusic.value};

	fetch("/movies/new", {
	  method: "POST",
	  headers: {'Content-Type': 'application/json'}, 
	  body: JSON.stringify(data)
	}).then(function(response) {
	    // The response is a Response instance.
	    // You parse the data into a useable format using `.json()`
	    return response.json();
	  }).then(function(data) {
	    // `data` is the parsed version of the JSON returned from the above endpoint.
	    console.log(data.movieID);  // { "userId": 1, "id": 1, "title": "...", "body": "..." }
	    console.log(data.title);
	    console.log(data.Status);
	    console.log(data.status);
	    
	    if(data.status==null)
	    	{
    			temp +=  "<div class='alert alert-primary' role='alert'>";
    			temp +=  data.Status+" ;-)";
    			temp +=  "</div>";
    		
    			temp += "<div class='container'><p><b>ID do vídeo: </b>"+data.movieID+"</p>";
			    temp += "<p><b>Title do vídeo: </b>"+data.title+"</p></div>";

			    
	    		document.getElementById("itemMusic").innerHTML = temp;
	    		document.getElementById("itemMusic").style.display = 'block';
	    		document.getElementById("listLoadingItemMusic").style.display = 'none';
	    	}else
	    		{
		    		temp +=  "<div class='alert alert-danger' role='alert'>";
		    		temp +=  "Não foi possível cadastrar ou já havia o cadastro ;-(";
		    		temp +=  "</div>";
		    		document.getElementById("itemMusic").innerHTML = temp;
		    		document.getElementById("itemMusic").style.display = 'block';
		    		document.getElementById("listLoadingItemMusic").style.display = 'none';
	    		}
	  });
}

function loadYoutube(tmpYoutube)
{
	var s="<iframe width='100%' height='600px' src='https://www.youtube.com/embed/"+tmpYoutube+"' title='YouTube video player' frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>";
	document.getElementById("playYoutube").innerHTML = s;
	document.getElementById("playYoutube").style.display = 'block';
	document.getElementById("initalPlayer").style.display = 'none';
	
	$('#myPlayList').modal('hide');
}


function searchMovies()
{
	document.getElementById("listSongs").style.display = 'none';
	document.getElementById("listLoading").style.display = 'block';
	
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/movies/findbytitle/"+txtSearch.value, false ); // false for synchronous request
    xmlHttp.send( null );
    const data = JSON.parse(xmlHttp.responseText);
    /*
    data.forEach(obj => {
        Object.entries(obj).forEach(([key, value]) => {
            console.log(`${key} ${value}`);
        });
        console.log('-------------------');
    });
    */
    var qtd = 0;
    var tmp = "";
    var tmpStr = "";
    
    data.forEach(obj => {
        Object.entries(obj).forEach(([key, value]) => {
        	if (key=='movieId') 
        		{ 			
        			console.log(new Date().getTime());
        			qtd +=1;
        		 	tmp += " <button onclick='loadYoutube(&quot;"+value+"&quot;)' type='button' class='btn btn-primary'>Selecionar</button> - ";
        		}
        	if (key=='movieTitle') 
    			{
    		 		tmp += value+"<hr>";
    			}
        });
    });
    
    if (tmp.length == 0)
    	{
    		tmpStr +=  "<div class='alert alert-danger' role='alert'>";
    		tmpStr +=  "Não encontramos sua música ;-(";
    		tmpStr +=  "</div>";
    	}else
    		{
    			tmpStr +=  "<div class='alert alert-primary' role='alert'>";
    			tmpStr +=  "Encontramos " +qtd+ " músicas relacionadas ;-):";
    			tmpStr +=  "</div>" + tmp;
    		}

    document.getElementById("listSongs").innerHTML = tmpStr;
	document.getElementById("listSongs").style.display = 'block';
	document.getElementById("listLoading").style.display = 'none';

}

function deleteYoutube()
{
	document.getElementById("listSongsDelete").style.display = 'none';
	document.getElementById("listLoadingDelete").style.display = 'block';
	
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/movies/delete/"+document.getElementById("videoDeletionID").innerText, false ); // false for synchronous request
    xmlHttp.send( null );
    const data = JSON.parse(xmlHttp.responseText);
    
    var tmpStr = "";
    
	tmpStr +=  "<div class='alert alert-danger' role='alert'>";
	tmpStr +=  data.status;
	tmpStr +=  "</div>";
    
    document.getElementById("listSongsDelete").innerHTML = tmpStr;
	document.getElementById("listSongsDelete").style.display = 'block';
	document.getElementById("listLoadingDelete").style.display = 'none';

}

function selectDeleteMovies(idMovie,titleMovie)
{
	document.getElementById("videoDeletionID").innerText = idMovie;
	document.getElementById("videoDeletionTitle").innerText = titleMovie;
}

function searchMoviesDelete()
{
	document.getElementById("listSongsDelete").style.display = 'none';
	document.getElementById("listLoadingDelete").style.display = 'block';
	
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/movies/findbytitle/"+txtSearchDelete.value, false ); // false for synchronous request
    xmlHttp.send( null );
    const data = JSON.parse(xmlHttp.responseText);
    /*
    data.forEach(obj => {
        Object.entries(obj).forEach(([key, value]) => {
            console.log(`${key} ${value}`);
        });
        console.log('-------------------');
    });
    */
    var qtd = 0;
    var tmp = "";
    var tmpStr = "";
    
    data.forEach(obj => {
        Object.entries(obj).forEach(([key, value]) => {
        	if (key=='movieId') 
        		{ 			
        			console.log(new Date().getTime());
        			qtd +=1;
        		 	tmp += " <button onclick='selectDeleteMovies(&quot;"+value+"&quot;,";
        		}
        	if (key=='movieTitle') 
    			{
    		 		tmp += "&quot;"+value+"&quot;)' type='button' class='btn btn-danger' href='#myModalConfirmDeletion' data-toggle='modal' >Deletar</button> - "+value+"<hr>";
    			}
        });
    });
    
    if (tmp.length == 0)
    	{
    		tmpStr +=  "<div class='alert alert-danger' role='alert'>";
    		tmpStr +=  "Não encontramos sua música ;-(";
    		tmpStr +=  "</div>";
    	}else
    		{
    			tmpStr +=  "<div class='alert alert-primary' role='alert'>";
    			tmpStr +=  "Encontramos " +qtd+ " músicas relacionadas ;-):";
    			tmpStr +=  "</div>" + tmp;
    		}


	
    document.getElementById("listSongsDelete").innerHTML = tmpStr;
	document.getElementById("listSongsDelete").style.display = 'block';
	document.getElementById("listLoadingDelete").style.display = 'none';

}
