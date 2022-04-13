


function closeDiv()
{
	document.getElementById("itemMusic").style.display = 'none';
	document.getElementById("listLoadingItemMusic").style.display = 'none';
	youtubeMusic.value='';
}

function clearRepertorio()
{
document.getElementById("txtSearch").value='';
document.getElementById("listSongs").style.display = 'none';
document.getElementById("listLoading").style.display = 'none';
}

function clearDeletar()
{
document.getElementById("txtSearchDelete").value='';
document.getElementById("listSongsDelete").style.display = 'none';
document.getElementById("listLoadingDelete").style.display = 'none';
}

function clearNewMusics()
{
document.getElementById("youtubeMusic").value='';
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

function checkOnline()
{
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/files/online", false ); // false for synchronous request
    xmlHttp.send( null );
    const data = JSON.parse(xmlHttp.responseText);
	     
    console.log('verificando se utilizará a versão online ou offline: '+data.online);

    return data.online;
}

function getPathLocalFile()
{
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/files/online", false ); // false for synchronous request
    xmlHttp.send( null );
    const data = JSON.parse(xmlHttp.responseText);
	     
    console.log('path file local player: '+data.path);

    return data.path;
}

function getFileToPlay(tmpYoutube)
{
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/files/"+tmpYoutube, false ); // false for synchronous request
    xmlHttp.send( null );
    const data = JSON.parse(xmlHttp.responseText);
	     
    console.log('file local player: '+data.filename0);

    return data.filename0;
}

function getFilex64(tmpYoutube)
{
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/files/getfile/"+tmpYoutube, false ); // false for synchronous request
    xmlHttp.send( null );
    const data = xmlHttp.responseText;
	console.log("x64 video:"+data);
    return data;
}

function loadYoutube(tmpYoutube)
{
document.getElementById("initalPlayerLoading").style.display = 'block';

	var s="";
	if (checkOnline() == "yes")
	{
		s="<iframe width='100%' height='600px' src='https://www.youtube.com/embed/"+tmpYoutube+"' title='YouTube video player' frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>";
		document.getElementById("playYoutube").innerHTML = s;
		document.getElementById("playYoutube").style.display = 'block';
		document.getElementById("initalPlayer").style.display = 'none';
		document.getElementById("playLocal").style.display = 'none';
		document.getElementById("initalPlayerLoading").style.display = 'none';
		
	}else
	{
		if (getFileToPlay(tmpYoutube) != "Empty directory or directory does not exists.")
		{
			s="<video autoplay id='player' src='data:video/mp4;base64,"+getFilex64(tmpYoutube)+"' controls height='75%' width='100%' ></video>";
			document.getElementById("playLocal").innerHTML = s;
			document.getElementById("playLocal").style.display = 'block';
			document.getElementById("initalPlayer").style.display = 'none';
			document.getElementById("playYoutube").style.display = 'none';	
			document.getElementById("initalPlayerLoading").style.display = 'none';
		}else
		{
			document.getElementById("playLocal").style.display = 'none';
			document.getElementById("initalPlayer").style.display = 'block';
			document.getElementById("playYoutube").style.display = 'none';	
			document.getElementById("initalPlayerLoading").style.display = 'none';
			$('#myModalConfirmNotFoundFile').modal('show');
		}	
	}
	
	$('#myPlayList').modal('hide');
	$('#addPlayList').modal('hide');
}

function deleteSingerList(tmpRequestNumber)
{
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/songs/deleteByRequestNumber/"+tmpRequestNumber, false ); // false for synchronous request
    xmlHttp.send( null );
   
   searchSingers();   
}

function addListYoutube()
{
	$('#myModalConfirmAddList').modal('hide');
	
	let time = new Date().getTime();
	let data = {'requestNumber':time,
				'name':yourNameHere.value,
				'songTitle':videoAddListTitle.innerText,
				'songID':videoAddListID.innerText};
	
	console.log(data);

	fetch("/songs/new", {
	  method: "POST",
	  headers: {'Content-Type': 'application/json'}, 
	  body: JSON.stringify(data)
	}).then(function(response) {
	    // The response is a Response instance.
	    // You parse the data into a useable format using `.json()`
	    return response.json();
	  }).then(function(data) {
	    // `data` is the parsed version of the JSON returned from the above endpoint.
	    console.log(data);
	    searchSingers();
	    document.getElementById("yourNameHere").value = '';
	  });
    
    
    
   
    $('#addPlayList').modal('show');
    searchSingers();
}


function searchSingers()
{
	var tmp = "";
	var req = "";
	document.getElementById("listSingers").style.display = 'none';
	document.getElementById("listLoandingSingers").style.display = 'block';
	
	var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", "/songs/findall", false ); // false for synchronous request
    xmlHttp.send( null );
    const data = JSON.parse(xmlHttp.responseText);
	    
    data.forEach(obj => {
        Object.entries(obj).forEach(([key, value]) => {
            console.log(`${key} ${value}`);
            if (key=="requestNumber")
            { req = value;
              tmp += "<button style='margin-top:-15px;' onclick='deleteSingerList(&quot;"+value+"&quot;)' type='button' class='btn btn-danger'>Remover da Lista</button> - "; 
			}
			if (key=="songID")
			tmp +=  "<button style='margin-top:-15px;' onclick='loadYoutube(&quot;"+value+"&quot;); deleteSingerList(&quot;"+req+"&quot;)' type='button' class='btn btn-success'>Tocar Música</button>  - ";
             if (key=="name")
			tmp += "<b style='font-size: 1.6rem;'>"+value +"</b> - ";
			if (key=="songTitle")
			tmp += value + "<hr>";
       });
        console.log('-------------------');
    });
    
    document.getElementById("listSingers").innerHTML = tmp;
	document.getElementById("listSingers").style.display = 'block';
	document.getElementById("listLoandingSingers").style.display = 'none';
        
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
        		 	tmp += " <button onclick='selectAddListMovies(&quot;"+value+"&quot;,";
        		}
        	if (key=='movieTitle') 
    			{
    		 		tmp += "&quot;"+value+"&quot;)' type='button' class='btn btn-primary' href='#myModalConfirmAddList' data-toggle='modal' >Selecionar</button> - "+value+"<hr>";
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

function selectAddListMovies(idMovie,titleMovie)
{
	
	$('#myPlayList').modal('hide');
	$('#addPlayList').modal('hide');
	
	document.getElementById("videoAddListID").innerText = idMovie;
	document.getElementById("videoAddListTitle").innerText = titleMovie;
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
