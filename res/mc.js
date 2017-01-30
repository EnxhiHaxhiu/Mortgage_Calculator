function validate()
{
	var ok = true;
	var p = document.getElementById("principle").value;

	if (isNaN(p) || p <= 0)
	{
		document.getElementById("principleError").innerHTML="<span style='color:red'>Invalid principle"

		ok = false;
	}
	if (!ok) return false;
	p = document.getElementById("interest").value;
	if (isNaN(p) || p <= 0 || p >= 100){
		document.getElementById("interestError").innerHTML="<span style='color:red'>Invalid Interest. It must be between [0-100]"
		ok = false;
	}
	
	if (!ok) return false;
	p = document.getElementById("period").value;
	if (isNaN(p) || p <= 0)
	{
		document.getElementById("periodError").innerHTML="<span style='color:red'>Invalid Period"
		ok = false;
	}

	
	return ok;
}