function stripAndExecuteScript(text) {
    var scripts = '';
    var cleaned = text.replace(/<script[^>]*>([\s\S]*?)<\/script>/gi, function(){
        scripts += arguments[1] + '\n';
        return '';
    });

    if (window.execScript){
        window.execScript(scripts);
    } else {
        var head = document.getElementsByTagName('head')[0];
        var scriptElement = document.createElement('script');
        scriptElement.setAttribute('type', 'text/javascript');
        scriptElement.innerText = scripts;
        head.appendChild(scriptElement);
        head.removeChild(scriptElement);
    }
    return cleaned;
};

function ajaxFormPost(strURL, dir, formname, responsediv, lang, mistakes, content) {
	strURL = strURL;

    var xmlHttpReq = false;
    var self = this;

    // Mozilla/Safari/Ie7
    if (window.XMLHttpRequest) {
        self.xmlHttpReq = new XMLHttpRequest();
    }

    // IE
    else if (window.ActiveXObject) {
        self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
    }

    self.xmlHttpReq.open('POST', strURL, true);
    self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    self.xmlHttpReq.onreadystatechange = function() {
        if (self.xmlHttpReq.readyState == 4) {
        	
        	ajaxContentPost(content, '', '', 'content', 'de');

        }

    }
    self.xmlHttpReq.send(getquerystring(formname) + '&lang=' + lang + '&mistakes=' + mistakes + '&content=' + content);
}

function getquerystring(formname) {
    var form = document.forms[formname];
	var qstr = "";


    function GetElemValue(name, value) {
        qstr += (qstr.length > 0 ? "&" : "")
            + escape(name).replace(/\+/g, "%2B") + "="
            + escape(value ? value : "").replace(/\+/g, "%2B");
			//+ escape(value ? value : "").replace(/\n/g, "%0D");
    }
	

	var elemArray = form.elements;
    for (var i = 0; i < elemArray.length; i++) {
        var element = elemArray[i];
        var elemType = element.type.toUpperCase();
        var elemName = element.name;
        if (elemName) {
            if (elemType == "TEXT"
                    || elemType == "TEXTAREA"
                    || elemType == "PASSWORD"
					|| elemType == "BUTTON"
					|| elemType == "RESET"
					|| elemType == "SUBMIT"
					|| elemType == "FILE"
					|| elemType == "IMAGE"
                    || elemType == "HIDDEN")

                GetElemValue(elemName, element.value);
            else if (elemType == "CHECKBOX" && element.checked)
                GetElemValue(elemName, 
                    element.value ? element.value : "On");
            else if (elemType == "RADIO" && element.checked)
                GetElemValue(elemName, element.value);
            else if (elemType.indexOf("SELECT") != -1)
                for (var j = 0; j < element.options.length; j++) {
                    var option = element.options[j];
                    if (option.selected)
                        GetElemValue(elemName,
                          option.value ? option.value : option.text);
                }
        }
    }
    return qstr;
}

function updatepage(str,responsediv){
	$('#'+responsediv).css("display", "none");
    document.getElementById(responsediv).innerHTML = str;
	$('#'+responsediv).fadeIn('fast');
	
}

// Content Post

var wait = 0;
var queue = new Array ();

function ajaxContentPost(strURL,dir,content,responsediv,lang) {
	strURLnew = '../' + dir +'/' + strURL;

    var xmlHttpReq = false;
    var self = this;

	if (wait == 0) {
	wait = 1; 
		// Mozilla/Safari/Ie7
		if (window.XMLHttpRequest) {
			self.xmlHttpReq = new XMLHttpRequest();
		}
		// IE
		else if (window.ActiveXObject) {
			self.xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
		}
		self.xmlHttpReq.open('POST', strURLnew, true);
		self.xmlHttpReq.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	
		self.xmlHttpReq.onreadystatechange = function() {
			if (self.xmlHttpReq.readyState == 4) {
				updatecontent(self.xmlHttpReq.responseText,responsediv);
				wait = 0;
				if (queue.length >= 1) {
					var entry = queue.shift();
					ajaxContentPost(entry[0], entry[1], entry[2], entry[3], entry[4]);	
				} else {
				$('#loading').fadeOut('fast');	
				}			
			}
			else {
				$('#loading').fadeIn('fast');
				// updatepage(responsemsg,responsediv);
			}
		}
	
		self.xmlHttpReq.send('content=' + content + '&lang=' + lang);
	} else {
		queue.push(new Array(strURL,dir,content,responsediv,lang));
	}
		
}


function updatecontent(str,responsediv){
	//$('#'+responsediv).css("display", "none");
//alert(str + 'dd');
//STR.find('#content').appendTo('#content');
//STR.find('script').appendTo('#content');
    document.getElementById(responsediv).innerHTML = stripAndExecuteScript(str);
	//$('#'+responsediv).fadeIn('fast');
    
	
}

function setactiv(mainnavid, mainnavmax) {
	document.getElementById('main' + mainnavid).setAttribute("class", "a_navi-top");
	for (var i = 1; i <= mainnavmax; i++)
	{
		if (i!= mainnavid)
		document.getElementById('main' + i).setAttribute("class", "navi-top");
	}
	
}

