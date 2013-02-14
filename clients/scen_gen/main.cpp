#include <iostream>
#include <fstream>
#include <vector>
#include "soapenrimaSoap11Proxy.h"
#include "enrimaSoap11.nsmap"
using namespace std;
enrimaSoap11Proxy enrimaSoap11Proxy(SOAP_XML_INDENT);
int getModel(int idModel, _ns1__getSMSResponse *ns1__getSMSResponse);
void printModel(ns2__modelSpec *model);
int main() {
	_ns1__getSMSResponse *response = new _ns1__getSMSResponse;
	int returnCode = getModel(71, response);
	if (returnCode == SOAP_OK)
	{
		printModel(response->modelSpec);
	}
}

int getModel(int idModel, _ns1__getSMSResponse *ns1__getSMSResponse) {
	_ns1__getSMSRequest *req = new _ns1__getSMSRequest;
	req->idModelSpec = idModel;
	if (enrimaSoap11Proxy.getSMS(req, ns1__getSMSResponse) == SOAP_OK)
	{
		return SOAP_OK;
	} else {
		enrimaSoap11Proxy.soap_stream_fault(cerr);
		return enrimaSoap11Proxy.error;
	}
}
void printModel(ns2__modelSpec *model) {
	cout << "model id: " << *model->id << " name: " << model->status << endl;
	for (unsigned short i = 0; i < model->setSpec.size(); i++) {
		ns2__setSpec *setSpec = model->setSpec.at(i);
		cout << "setSpec id: " << *setSpec->id << ", label:" << *setSpec->name<< endl;
	}
	for (unsigned short i = 0; i < model->entitySpec.size(); i++) {
		ns2__entitySpec *entitySpec = model->entitySpec.at(i);
		cout << "entitySpec id: " << *entitySpec->id << ", label:"<< *entitySpec->name << endl;
	}
}
