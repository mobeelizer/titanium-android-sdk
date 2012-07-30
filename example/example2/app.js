// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.


// open a single window
var win = Ti.UI.createWindow({
	backgroundColor:'white'
});

var view = Ti.UI.createView({
	backgroundColor:'transparent',
	top:0,
	left:0,
	width:'100%',
	height:'100%',
	layout:'vertical'
});
win.add(view);

var LABELS_NUMBER = 25;
var labels = new Array();
for (var i=0; i<LABELS_NUMBER; i++) {
	var label = Ti.UI.createLabel();
	view.add(label);
	labels[i] = label;
}

win.open();

function addEntity(name, boolValue, intValue) {
	var entity = database.entity("Director");
	entity.putField("name", name);
	entity.putField("boolField", boolValue);
	entity.putField("integerField", intValue);
	var errors = database.save(entity);
	if (errors == null) {
		Ti.API.info("################# => entity '"+name+"', '"+boolValue+"' saved");
	} else {
		Ti.API.info("################# => ERROR: entity '"+name+"' not saved");
		throw errors;
	}
}

var Mobeelizer = require('ti.mobeelizer.sdk');

Mobeelizer.loginAndWait("u1", "pass");

var database = Mobeelizer.getDatabase();

Ti.API.info("################# => BEGIN");

//---------------------------------------

database.removeAll("Director");

var entity = database.entity("Director");
entity.putField("name", "testName");
entity.putField("boolField", true);
entity.putField("dateField", new Date(100));
entity.putField("decimalField", 10.1);
entity.putField("integerField", 33);

var errors = database.save(entity);
if (errors) {
	Ti.API.info(errors);
	throw "exception!!!";
}

Ti.API.info("##### valid save status");

function assertCount(expected, value) {
	if(expected != value) {
	    throw "##### invalid count, should be "+expected+" => "+value;   
	} else {
	    Ti.API.info("##### valid count status");
	}	
}

Ti.API.info("##### TEST 1");
assertCount(1, database.find("Director").add(database.Restriction.eq("name", "testName")).count());
Ti.API.info("##### TEST 2");
assertCount(0, database.find("Director").add(database.Restriction.eq("name", "testName1")).count());

Ti.API.info("##### TEST 3");
assertCount(1, database.find("Director").add(database.Restriction.eq("boolField", true)).count());
Ti.API.info("##### TEST 4");
assertCount(0, database.find("Director").add(database.Restriction.eq("boolField", false)).count());

Ti.API.info("##### TEST 5");
assertCount(1, database.find("Director").add(database.Restriction.eq("dateField", new Date(100))).count());
Ti.API.info("##### TEST 6");
assertCount(0, database.find("Director").add(database.Restriction.eq("dateField", new Date(101))).count());

Ti.API.info("##### TEST 7");
assertCount(1, database.find("Director").add(database.Restriction.eq("decimalField", 10.1)).count());
Ti.API.info("##### TEST 8");
assertCount(0, database.find("Director").add(database.Restriction.eq("decimalField", 10.2)).count());

Ti.API.info("##### TEST 9");
assertCount(1, database.find("Director").add(database.Restriction.eq("integerField", 33)).count());
Ti.API.info("##### TEST 10");
assertCount(0, database.find("Director").add(database.Restriction.eq("integerField", 32)).count());

// ---------------------------------------

var errors = database.removeAll("Director");
if (errors) {
	throw errors;
}

addEntity("BBB", true, 1);
addEntity("BBB", false, 5);
addEntity("AAA", false, 3);
addEntity("CCC", true, 6);
addEntity("ABC", true, 9);

Ti.API.info("##### TEST 11");
assertCount(4, database.find("Director").add(database.Restriction.disjunction().add(database.Restriction.eq("name", "BBB")).add(database.Restriction.eq("boolField", true))).count());
Ti.API.info("##### TEST 12");
assertCount(1, database.find("Director").add(database.Restriction.conjunction().add(database.Restriction.eq("name", "BBB")).add(database.Restriction.eq("boolField", true))).count());
Ti.API.info("##### TEST 13");
assertCount(1, database.find("Director").add(database.Restriction.inArray("integerField", [1])).count());
Ti.API.info("##### TEST 14");
assertCount(2, database.find("Director").add(database.Restriction.inArray("integerField", [1,3,2])).count());
Ti.API.info("##### TEST 15");
assertCount(1, database.find("Director").add(database.Restriction.like("name", "ABC")).count());
Ti.API.info("##### TEST 16");
assertCount(0, database.find("Director").add(database.Restriction.like("name", "BC", database.Restriction.MATCH_MODE_START)).count());
Ti.API.info("##### TEST 17");
assertCount(1, database.find("Director").add(database.Restriction.like("name", "BC", database.Restriction.MATCH_MODE_END)).count());
Ti.API.info("##### TEST 18");
assertCount(1, database.find("Director").add(database.Restriction.like("name", "BC", database.Restriction.MATCH_MODE_ANYWHERE)).count());
Ti.API.info("##### TEST 19");
assertCount(3, database.find("Director").add(database.Restriction.like("name", "B", database.Restriction.MATCH_MODE_ANYWHERE)).count());

Ti.API.info("################# => END");
