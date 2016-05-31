function run(){
	var appContainer = document.getElementsByClassName('mainpart')[0];

	appContainer.addEventListener('click', delegateEvent);
	appContainer.addEventListener('change', delegateEvent);

	updateCounter();
}

function delegateEvent(evtObj) {
	if(evtObj.type === 'click' && evtObj.target.classList.contains('newmesbutton')){
		onSendMessageClick(evtObj);
	}
	if(evtObj.type === 'change' && evtObj.target.nodeName == 'INPUT'){
		var labelEl = evtObj.target.parentElement;

		onToggleItem(labelEl);
	}
}

function onSendMessageClick(){
	var todoText = document.getElementById('NewMessageText');

	addTodo(todoText.value);
	todoText.value = '';
	updateCounter();
} 

function onToggleItem(labelEl) {
	if(labelEl.classList.contains('strikeout')) {
		labelEl.classList.remove('strikeout');
	}
	else {
		labelEl.classList.add('strikeout');
	}
	updateCounter();
}

function addTodo(value) {
	if(!value){
		return;
	}

	var item = createItem(value);
	var items = document.getElementsByClassName('message-list')[0];

	items.appendChild(item);
	updateCounter();
}

function createItem(text){
	var divItem = document.createElement('li');
	
	divItem.classList.add('message');

	divItem.appendChild(document.createTextNode(text));

	return divItem;
}

function updateCounter(){
	var items = document.getElementsByClassName('message-list')[0];
	var counter = document.getElementsByClassName('message-number')[0];

    counter.innerText = items.children.length.toString();
}