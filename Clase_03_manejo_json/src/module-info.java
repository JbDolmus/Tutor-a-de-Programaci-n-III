
module Clase_03_manejo_json {
	requires com.fasterxml.jackson.databind;
	requires com.fasterxml.jackson.datatype.jsr310;

	exports domain;
	opens business;
}