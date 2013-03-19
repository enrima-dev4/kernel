CREATE TABLE active_entity
(
	id_model              INTEGER  NOT NULL ,
	id_submodel           INTEGER  NOT NULL ,
	id_entity_spec        INTEGER  NOT NULL ,
	activity_status       CHAR(1)  NULL 
);



ALTER TABLE active_entity
	ADD CONSTRAINT  XPKactive_entity PRIMARY KEY (id_model,id_submodel,id_entity_spec);



CREATE TABLE active_set
(
	id_model              INTEGER  NOT NULL ,
	id_submodel           INTEGER  NOT NULL ,
	id_set_spec           INTEGER  NOT NULL ,
	activity_status       CHAR(1)  NULL 
);



ALTER TABLE active_set
	ADD CONSTRAINT  XPKactive_set PRIMARY KEY (id_model,id_submodel,id_set_spec);



CREATE TABLE analysis_type
(
	id_analysis_type      INTEGER  NOT NULL ,
	analysis_type_name    VARCHAR(20)  NOT NULL 
);



ALTER TABLE analysis_type
	ADD CONSTRAINT  XPKanalysis_type PRIMARY KEY (id_analysis_type);



CREATE TABLE app_instance
(
	id                    INTEGER  NOT NULL ,
	src_revision          INTEGER  NULL ,
	db_structure_version  INTEGER  NULL ,
	db_structure_created  TIMESTAMP  NULL ,
	installers_run        TIMESTAMP  NULL ,
	installation_type     VARCHAR(20)  NULL 
);



ALTER TABLE app_instance
	ADD CONSTRAINT  XPKapp_instance PRIMARY KEY (id);



CREATE TABLE comments
(
	id_comment            CHAR(18)  NOT NULL ,
	id_model              INTEGER  NULL ,
	id_submodel           INTEGER  NULL ,
	id_update             INTEGER  NULL ,
	comment_str           VARCHAR(1000)  NULL ,
	id_spec_rev           INTEGER  NULL 
);



ALTER TABLE comments
	ADD CONSTRAINT  XPKcomments PRIMARY KEY (id_comment);



CREATE TABLE computational_task
(
	id_task               INTEGER  NOT NULL ,
	id_analysis           INTEGER  NULL ,
	id_host               INTEGER  NULL ,
	id_solver             INTEGER  NULL ,
	id_results_storage    INTEGER  NULL ,
	creation_date         TIMESTAMP  NOT NULL ,
	created_by            INTEGER  NOT NULL ,
	short_desc            VARCHAR(20)  NULL ,
	long_desc             VARCHAR(1000)  NULL 
);



ALTER TABLE computational_task
	ADD CONSTRAINT  XPKcomputational_task PRIMARY KEY (id_task);



CREATE TABLE db_sts
(
	id_db_sts             CHAR(1)  NOT NULL ,
	msg                   VARCHAR(64)  NULL 
);



ALTER TABLE db_sts
	ADD CONSTRAINT  XPKdb_sts PRIMARY KEY (id_db_sts);



CREATE TABLE entity_attr
(
	id_entity_attr        INTEGER  NOT NULL ,
	id_category           INTEGER  NOT NULL ,
	id_entity_rev         INTEGER  NOT NULL ,
	id_attr_cat_el        INTEGER  NOT NULL 
);



ALTER TABLE entity_attr
	ADD CONSTRAINT  XPKentity_attr PRIMARY KEY (id_entity_attr);



ALTER TABLE entity_attr
ADD CONSTRAINT  XAK1entity_attr UNIQUE (id_category,id_entity_rev);



CREATE TABLE entity_attr_category
(
	id_category           INTEGER  NOT NULL ,
	category_name         VARCHAR(20)  NOT NULL ,
	cat_order             INTEGER  NULL,
	id_model             INTEGER  NULL 
);



ALTER TABLE entity_attr_category
	ADD CONSTRAINT  XPKentity_attr_category PRIMARY KEY (id_category);



CREATE TABLE entity_attr_category_element
(
	id_attr_cat_el        INTEGER  NOT NULL ,
	id_category           INTEGER  NOT NULL ,
	element_no            INTEGER  NOT NULL ,
	code                  INTEGER  NULL ,
	value                 VARCHAR(20)  NOT NULL 
);



ALTER TABLE entity_attr_category_element
	ADD CONSTRAINT  XPKentity_attr_category_elemen PRIMARY KEY (id_attr_cat_el);



CREATE TABLE entity_role
(
	role_code             INTEGER  NOT NULL ,
	role_name             VARCHAR(30)  NOT NULL 
);



ALTER TABLE entity_role
	ADD CONSTRAINT  XPKentity_role PRIMARY KEY (role_code);



CREATE TABLE entity_spec
(
	id_entity_spec        INTEGER  NOT NULL ,
	id_model              INTEGER  NOT NULL ,
	label                 VARCHAR(16)  NOT NULL ,
	commited              CHAR(1)  NOT NULL ,
	position              INTEGER  NOT NULL ,
	constant_value        FLOAT  NULL ,
	data_flag             CHAR(1)  NULL 
);



ALTER TABLE entity_spec
	ADD CONSTRAINT  XPKentity_spec PRIMARY KEY (id_entity_spec);



ALTER TABLE entity_spec
ADD CONSTRAINT  XAK1entity_spec UNIQUE (id_model,label);



CREATE TABLE entity_spec_ver
(
	id_entity_rev         INTEGER  NOT NULL ,
	id_entity_spec        INTEGER  NULL ,
	id_spec_rev           INTEGER  NULL ,
	role_code             INTEGER  NOT NULL ,
	id_associated_entity  INTEGER  NULL ,
	id_upper_bound        INTEGER  NULL ,
	id_lower_bound        INTEGER  NULL ,
	creation_date         TIMESTAMP  NULL ,
	created_by            INTEGER  NULL ,
	active                INTEGER  NOT NULL ,
	short_desc            VARCHAR(64)  NULL ,
	long_desc             VARCHAR(1000)  NULL ,
	id_iter_cont          INTEGER  NULL,
	formula               VARCHAR(1000)  NULL,
	id_unit               INTEGER  NULL
);



ALTER TABLE entity_spec_ver
	ADD CONSTRAINT  XPKentity_spec_ver PRIMARY KEY (id_entity_rev);



ALTER TABLE entity_spec_ver
ADD CONSTRAINT  XAK1entity_spec_ver UNIQUE (id_entity_spec,id_spec_rev);



CREATE TABLE entity_values
(
	id_entity_spec        INTEGER  NOT NULL ,
	id_update             INTEGER  NOT NULL ,
	id_tuple              INTEGER  NOT NULL ,
	value                 DOUBLE PRECISION  NOT NULL 
);



ALTER TABLE entity_values
	ADD CONSTRAINT  XPKentity_values PRIMARY KEY (id_entity_spec,id_update,id_tuple);



CREATE TABLE file_upload_info
(
	id_file_upload        INTEGER  NOT NULL ,
	id_update             INTEGER  NULL ,
	file_location         VARCHAR(256)  NOT NULL ,
	file_name             VARCHAR(30)  NOT NULL ,
	file_date             TIMESTAMP  NOT NULL ,
	file_author           VARCHAR(20)  NULL ,
	data_date             TIMESTAMP  NOT NULL ,
	data_comment          VARCHAR(1000)  NULL 
);



ALTER TABLE file_upload_info
	ADD CONSTRAINT  XPKfile_upload_info PRIMARY KEY (id_file_upload);



CREATE TABLE host
(
	id_host               INTEGER  NOT NULL ,
	name                  VARCHAR(30)  NOT NULL 
);



ALTER TABLE host
	ADD CONSTRAINT  XPKhost PRIMARY KEY (id_host);



CREATE TABLE instance_state
(
	id_state              INTEGER  NOT NULL ,
	state_name            VARCHAR(20)  NOT NULL 
);



ALTER TABLE instance_state
	ADD CONSTRAINT  XPKinstance_state PRIMARY KEY (id_state);



CREATE TABLE iterator_atom
(
	id_iter_atom          INTEGER  NOT NULL ,
	id_model              INTEGER  NULL ,
	id_set_spec           INTEGER  NOT NULL ,
	level                 INTEGER  NOT NULL ,
	role                  VARCHAR(50)  NULL ,
	id_iter_cont          INTEGER  NULL 
);



ALTER TABLE iterator_atom
	ADD CONSTRAINT  XPKiterator_atom PRIMARY KEY (id_iter_atom);



ALTER TABLE iterator_atom
ADD CONSTRAINT  XAK1iterator_atom UNIQUE (id_model,id_set_spec,level,role,id_iter_cont);



CREATE TABLE iterator_container
(
	id_iter_cont          INTEGER  NOT NULL ,
	id_model              INTEGER  NULL 
);



ALTER TABLE iterator_container
	ADD CONSTRAINT  XPKiterator_container PRIMARY KEY (id_iter_cont);



CREATE TABLE iterator_in_container
(
	id_iter_cont          INTEGER  NOT NULL ,
	id_iter_atom          INTEGER  NOT NULL ,
	sequence              INTEGER  NOT NULL 
);



ALTER TABLE iterator_in_container
	ADD CONSTRAINT  XPKiterator_in_container PRIMARY KEY (id_iter_cont,id_iter_atom,sequence);



CREATE TABLE members_dictionary
(
	id_member             INTEGER  NOT NULL ,
	id_set                INTEGER  NOT NULL ,
	code                  VARCHAR(8)  NOT NULL ,
	description           VARCHAR(50)  NULL 
);



ALTER TABLE members_dictionary
	ADD CONSTRAINT  XPKmembers_dictionary PRIMARY KEY (id_member);



ALTER TABLE members_dictionary
ADD CONSTRAINT  XAK1members_dictionary UNIQUE (id_set,code);



CREATE TABLE model
(
	id_model              INTEGER  NOT NULL ,
	major_label           VARCHAR(16)  NOT NULL ,
	id_model_type         INTEGER  NULL ,
	creation_date         TIMESTAMP  NOT NULL ,
	modification_date     TIMESTAMP  NOT NULL ,
	created_by            INTEGER  NULL ,
	modified_by           INTEGER  NULL ,
	id_model_template     INTEGER  NULL ,
	minor_label           VARCHAR(30)  NULL ,
	name                  VARCHAR(50)  NULL ,
	description           VARCHAR(1000)  NULL 
);



ALTER TABLE model
	ADD CONSTRAINT  XPKmodel PRIMARY KEY (id_model);



CREATE TABLE model_analysis
(
	id_analysis           INTEGER  NOT NULL ,
	id_instance           INTEGER  NOT NULL ,
	label                 VARCHAR(12)  NOT NULL ,
	id_analysis_type      INTEGER  NULL ,
	creation_date         TIMESTAMP  NOT NULL ,
	created_by            INTEGER  NOT NULL ,
	short_desc            VARCHAR(20)  NULL ,
	long_desc             VARCHAR(1000)  NULL 
);



ALTER TABLE model_analysis
	ADD CONSTRAINT  XPKmodel_analysis PRIMARY KEY (id_analysis);



CREATE TABLE model_data_update
(
	id_update             INTEGER  NOT NULL ,
	id_model              INTEGER  NULL ,
	parent_id_update      INTEGER  NULL ,
	locked                INTEGER  NULL ,
	db_sts                CHAR(1)  NULL ,
	sn                    VARCHAR(256)  NULL ,
	lck_sts               VARCHAR(256)  NULL ,
	update_date           TIMESTAMP  NULL ,
	updated_by            INTEGER  NULL ,
	view_status           INTEGER  NULL ,
	description           VARCHAR(1000)  NULL 
);



ALTER TABLE model_data_update
	ADD CONSTRAINT  XPKmodel_data_update PRIMARY KEY (id_update);



CREATE TABLE model_identifiers
(
	id_model              INTEGER  NOT NULL ,
	next_spec_rev         INTEGER  NULL ,
	_default_             CHAR(18)  NULL ,
	next_submodel_id      INTEGER  NULL ,
	next_constraint_lbl   INTEGER  NULL 
);



ALTER TABLE model_identifiers
	ADD CONSTRAINT  XPKmodel_identifiers PRIMARY KEY (id_model);



CREATE TABLE model_instance
(
	id_instance           INTEGER  NOT NULL ,
	id_model              INTEGER  NULL ,
	id_spec_rev           INTEGER  NULL ,
	id_update             INTEGER  NULL ,
	label                 VARCHAR(20)  NULL ,
	id_state              INTEGER  NOT NULL ,
	creation_date         TIMESTAMP  NULL ,
	created_by            INTEGER  NOT NULL ,
	short_desc            VARCHAR(20)  NULL ,
	long_desc             VARCHAR(1000)  NULL 
);



ALTER TABLE model_instance
	ADD CONSTRAINT  XPKmodel_instance PRIMARY KEY (id_instance);



CREATE TABLE model_spec_rev
(
	id_spec_rev           INTEGER  NOT NULL ,
	id_model              INTEGER  NULL ,
	rev_no                INTEGER  NULL ,
	parent_id_spec_rev    INTEGER  NULL ,
	id_spec_state         INTEGER  NULL ,
	db_sts                CHAR(1)  NULL ,
	sn                    VARCHAR(256)  NULL ,
	lck_sts               VARCHAR(256)  NULL ,
	creation_date         TIMESTAMP  NOT NULL ,
	modification_date     TIMESTAMP  NULL ,
	commit_date           TIMESTAMP  NULL ,
	created_by            INTEGER  NOT NULL ,
	modified_by           INTEGER  NULL ,
	commited_by           INTEGER  NULL ,
	short_desc            VARCHAR(64)  NULL ,
	long_desc             VARCHAR(1000)  NULL 
);



ALTER TABLE model_spec_rev
	ADD CONSTRAINT  XPKmodel_spec_rev PRIMARY KEY (id_spec_rev);



ALTER TABLE model_spec_rev
ADD CONSTRAINT  XAK1model_spec_rev UNIQUE (id_model,rev_no);



CREATE TABLE model_type
(
	id_model_type         INTEGER  NOT NULL ,
	type_name             VARCHAR(20)  NULL 
);



ALTER TABLE model_type
	ADD CONSTRAINT  XPKmodel_type PRIMARY KEY (id_model_type);



CREATE TABLE parameter_values_view
(
	id_update             INTEGER  NOT NULL ,
	id_entity_spec        INTEGER  NOT NULL ,
	id_tuple              INTEGER  NOT NULL ,
	value                 DOUBLE PRECISION  NOT NULL 
);



ALTER TABLE parameter_values_view
	ADD CONSTRAINT  XPKparameter_values_view PRIMARY KEY (id_update,id_entity_spec,id_tuple);



CREATE TABLE preference
(
	id_preference         CHAR(18)  NOT NULL ,
	id_analysis           INTEGER  NOT NULL ,
	id_pref_type          INTEGER  NULL ,
	id_entity_spec        INTEGER  NULL ,
	pref_value            DOUBLE PRECISION  NULL 
);



ALTER TABLE preference
	ADD CONSTRAINT  XPKpreference PRIMARY KEY (id_preference);



CREATE TABLE preference_type
(
	id_pref_type          INTEGER  NOT NULL ,
	pref_type_name        VARCHAR(20)  NOT NULL 
);



ALTER TABLE preference_type
	ADD CONSTRAINT  XPKpreference_type PRIMARY KEY (id_pref_type);



CREATE TABLE results_storage_type
(
	id_results_storage    INTEGER  NOT NULL ,
	results_storage_name  CHAR(18)  NOT NULL 
);



ALTER TABLE results_storage_type
	ADD CONSTRAINT  XPKresults_storage_type PRIMARY KEY (id_results_storage);



CREATE TABLE set_attr
(
	id_set_attr           INTEGER  NOT NULL ,
	id_set_rev            INTEGER  NOT NULL ,
	id_category           INTEGER  NULL ,
	element_no            INTEGER  NOT NULL 
);



ALTER TABLE set_attr
	ADD CONSTRAINT  XPKset_attr PRIMARY KEY (id_set_attr);



ALTER TABLE set_attr
ADD CONSTRAINT  XAK1set_attr UNIQUE (id_set_rev,id_category);



CREATE TABLE set_attr_category
(
	id_category           INTEGER  NOT NULL ,
	category_name         VARCHAR(30)  NOT NULL ,
	cat_order             INTEGER  NULL,
	id_model             INTEGER  NULL 
);



ALTER TABLE set_attr_category
	ADD CONSTRAINT  XPKset_attr_category PRIMARY KEY (id_category);



CREATE TABLE set_attr_category_element
(
	id_category           INTEGER  NOT NULL ,
	element_no            INTEGER  NOT NULL ,
	code                  INTEGER  NULL ,
	value                 VARCHAR(30)  NOT NULL 
);



ALTER TABLE set_attr_category_element
	ADD CONSTRAINT  XPKset_attr_category_element PRIMARY KEY (id_category,element_no);



CREATE TABLE set_data
(
	id_set                INTEGER  NOT NULL ,
	id_set_spec           INTEGER  NOT NULL ,
	id_tuple              INTEGER  NULL 
);



ALTER TABLE set_data
	ADD CONSTRAINT  XPKset_data PRIMARY KEY (id_set);



ALTER TABLE set_data
ADD CONSTRAINT  XAK1set_data UNIQUE (id_set_spec,id_tuple);



CREATE TABLE set_member
(
	id_set                INTEGER  NOT NULL ,
	id_update             INTEGER  NOT NULL ,
	id_member             INTEGER  NOT NULL ,
	is_member             INTEGER  NULL 
);



ALTER TABLE set_member
	ADD CONSTRAINT  XPKset_member PRIMARY KEY (id_set,id_update,id_member);



CREATE TABLE set_member_view
(
	id_update             INTEGER  NOT NULL ,
	id_set                INTEGER  NOT NULL ,
	id_tuple              INTEGER  NOT NULL ,
	member_code           VARCHAR(20)  NOT NULL 
);



ALTER TABLE set_member_view
	ADD CONSTRAINT  XPKset_member_view PRIMARY KEY (id_update,id_set,id_tuple,member_code);



CREATE TABLE set_spec
(
	id_set_spec           INTEGER  NOT NULL ,
	id_model              INTEGER  NULL ,
	label                 VARCHAR(16)  NOT NULL ,
	commited              CHAR(1)  NOT NULL ,
	parent_id_set         INTEGER  NULL ,
	id_iter_cont          INTEGER  NULL ,
	position              INTEGER  NOT NULL 
);



ALTER TABLE set_spec
	ADD CONSTRAINT  XPKset_spec PRIMARY KEY (id_set_spec);



ALTER TABLE set_spec
ADD CONSTRAINT  XAK1set_spec UNIQUE (id_model,label);



ALTER TABLE set_spec
ADD CONSTRAINT  XAK2set_spec UNIQUE (id_model,position);



CREATE TABLE set_spec_ver
(
	id_set_rev            INTEGER  NOT NULL ,
	id_set_spec           INTEGER  NULL ,
	id_spec_rev           INTEGER  NULL ,
	creation_date         TIMESTAMP  NOT NULL ,
	created_by            INTEGER  NOT NULL ,
	active                INTEGER  NOT NULL ,
	formula               VARCHAR(1000)  NULL ,
	short_desc            VARCHAR(64)  NULL ,
	long_desc             VARCHAR(1000)  NULL 
);



ALTER TABLE set_spec_ver
	ADD CONSTRAINT  XPKset_spec_ver PRIMARY KEY (id_set_rev);



ALTER TABLE set_spec_ver
ADD CONSTRAINT  XAK1set_spec_ver UNIQUE (id_set_spec,id_spec_rev);



CREATE TABLE sn
(
	id_sn                 VARCHAR(256)  NOT NULL ,
	_default_             CHAR(18)  NULL ,
	usr                   SMALLINT  NULL ,
	started               TIMESTAMP  NOT NULL ,
	touched               TIMESTAMP  NULL ,
	finished              TIMESTAMP  NULL 
);



ALTER TABLE sn
	ADD CONSTRAINT  XPKsn PRIMARY KEY (id_sn);



CREATE TABLE solver
(
	id_solver             INTEGER  NOT NULL ,
	name                  VARCHAR(20)  NOT NULL 
);



ALTER TABLE solver
	ADD CONSTRAINT  XPKsolver PRIMARY KEY (id_solver);



CREATE TABLE spec_state
(
	id_spec_state         INTEGER  NOT NULL ,
	state_name            VARCHAR(20)  NOT NULL 
);



ALTER TABLE spec_state
	ADD CONSTRAINT  XPKspec_state PRIMARY KEY (id_spec_state);



CREATE TABLE submodel
(
	id_model              INTEGER  NOT NULL ,
	id_submodel           INTEGER  NOT NULL ,
	position              INTEGER  NOT NULL 
);



ALTER TABLE submodel
	ADD CONSTRAINT  XPKsubmodel PRIMARY KEY (id_model,id_submodel);



CREATE TABLE term
(
	id_term               INTEGER  NOT NULL ,
	id_entity_rev         INTEGER  NOT NULL ,
	sequence              INTEGER  NOT NULL ,
	term_sign             CHAR(1)  NOT NULL 
);



ALTER TABLE term
	ADD CONSTRAINT  XPKterm PRIMARY KEY (id_term);



ALTER TABLE term
ADD CONSTRAINT  XAK1term UNIQUE (id_entity_rev,sequence);



CREATE TABLE term_entity
(
	id_term               INTEGER  NOT NULL ,
	id_entity_spec        INTEGER  NOT NULL ,
	sequence              INTEGER  NOT NULL ,
	id_iter_cont          INTEGER  NULL 
);



ALTER TABLE term_entity
	ADD CONSTRAINT  XPKterm_entity PRIMARY KEY (id_term,id_entity_spec,sequence);



CREATE TABLE tuple
(
	id_tuple              INTEGER  NOT NULL ,
	id_iter_cont          INTEGER  NULL 
);



ALTER TABLE tuple
	ADD CONSTRAINT  XPKtuple PRIMARY KEY (id_tuple);



CREATE TABLE tuple_element
(
	id_tuple              INTEGER  NOT NULL ,
	id_member             INTEGER  NOT NULL ,
	position              INTEGER  NOT NULL 
);


CREATE TABLE unit_dic
(
	id_unit               INTEGER  NOT NULL ,
	id_model              INTEGER NULL,
	unit                  VARCHAR(64)  NULL,
	description           VARCHAR(128)  NULL
);

ALTER TABLE unit_dic
	ADD CONSTRAINT  XPKunit_dic PRIMARY KEY (id_unit);
	
ALTER TABLE tuple_element
	ADD CONSTRAINT  XPKtuple_element PRIMARY KEY (id_tuple,id_member,position);



CREATE TABLE uploaded_entity
(
	id_uploaded_entity    INTEGER  NOT NULL ,
	id_file_upload        INTEGER  NULL ,
	id_entity_spec        INTEGER  NULL 
);



ALTER TABLE uploaded_entity
	ADD CONSTRAINT  XPKuploaded_entity PRIMARY KEY (id_uploaded_entity);



CREATE TABLE uploaded_set
(
	id_uploaded_set       INTEGER  NOT NULL ,
	id_file_upload        INTEGER  NULL ,
	id_set_spec           INTEGER  NULL 
);



ALTER TABLE uploaded_set
	ADD CONSTRAINT  XPKuploaded_set PRIMARY KEY (id_uploaded_set);



CREATE TABLE Usr
(
	id_Usr                INTEGER  NOT NULL 
);



ALTER TABLE Usr
	ADD CONSTRAINT  XPKUsr PRIMARY KEY (id_Usr);



ALTER TABLE active_entity
	ADD CONSTRAINT  R_220 FOREIGN KEY (id_model,id_submodel) REFERENCES submodel(id_model,id_submodel);



ALTER TABLE active_entity
	ADD CONSTRAINT  R_221 FOREIGN KEY (id_entity_spec) REFERENCES entity_spec(id_entity_spec);



ALTER TABLE active_set
	ADD CONSTRAINT  R_222 FOREIGN KEY (id_model,id_submodel) REFERENCES submodel(id_model,id_submodel);



ALTER TABLE active_set
	ADD CONSTRAINT  R_223 FOREIGN KEY (id_set_spec) REFERENCES set_spec(id_set_spec);



ALTER TABLE comments
	ADD CONSTRAINT  R_224 FOREIGN KEY (id_spec_rev) REFERENCES model_spec_rev(id_spec_rev) ON DELETE SET NULL;



ALTER TABLE comments
	ADD CONSTRAINT  R_225 FOREIGN KEY (id_model) REFERENCES model(id_model) ON DELETE SET NULL;



ALTER TABLE comments
	ADD CONSTRAINT  R_226 FOREIGN KEY (id_update) REFERENCES model_data_update(id_update) ON DELETE SET NULL;



ALTER TABLE comments
	ADD CONSTRAINT  R_227 FOREIGN KEY (id_model,id_submodel) REFERENCES submodel(id_model,id_submodel) ON DELETE SET NULL;



ALTER TABLE computational_task
	ADD CONSTRAINT  R_287 FOREIGN KEY (id_analysis) REFERENCES model_analysis(id_analysis) ON DELETE SET NULL;



ALTER TABLE computational_task
	ADD CONSTRAINT  R_288 FOREIGN KEY (id_host) REFERENCES host(id_host) ON DELETE SET NULL;



ALTER TABLE computational_task
	ADD CONSTRAINT  R_289 FOREIGN KEY (id_solver) REFERENCES solver(id_solver) ON DELETE SET NULL;



ALTER TABLE computational_task
	ADD CONSTRAINT  R_290 FOREIGN KEY (id_results_storage) REFERENCES results_storage_type(id_results_storage) ON DELETE SET NULL;



ALTER TABLE computational_task
	ADD CONSTRAINT  R_293 FOREIGN KEY (created_by) REFERENCES Usr(id_Usr);



ALTER TABLE entity_attr
	ADD CONSTRAINT  R_71 FOREIGN KEY (id_category) REFERENCES entity_attr_category(id_category);



ALTER TABLE entity_attr
	ADD CONSTRAINT  R_72 FOREIGN KEY (id_attr_cat_el) REFERENCES entity_attr_category_element(id_attr_cat_el) ON DELETE SET NULL;



ALTER TABLE entity_attr
	ADD CONSTRAINT  R_70 FOREIGN KEY (id_entity_rev) REFERENCES entity_spec_ver(id_entity_rev);



ALTER TABLE entity_attr_category_element
	ADD CONSTRAINT  R_69 FOREIGN KEY (id_category) REFERENCES entity_attr_category(id_category);



ALTER TABLE entity_spec
	ADD CONSTRAINT  R_5 FOREIGN KEY (id_model) REFERENCES model(id_model);



ALTER TABLE entity_spec_ver
	ADD CONSTRAINT  R_117 FOREIGN KEY (created_by) REFERENCES Usr(id_Usr) ON DELETE SET NULL;



ALTER TABLE entity_spec_ver
	ADD CONSTRAINT  R_190 FOREIGN KEY (id_associated_entity) REFERENCES entity_spec(id_entity_spec) ON DELETE SET NULL;



ALTER TABLE entity_spec_ver
	ADD CONSTRAINT  R_191 FOREIGN KEY (id_lower_bound) REFERENCES entity_spec(id_entity_spec) ON DELETE SET NULL;



ALTER TABLE entity_spec_ver
	ADD CONSTRAINT  R_192 FOREIGN KEY (id_upper_bound) REFERENCES entity_spec(id_entity_spec) ON DELETE SET NULL;



ALTER TABLE entity_spec_ver
	ADD CONSTRAINT  R_239 FOREIGN KEY (id_spec_rev) REFERENCES model_spec_rev(id_spec_rev) ON DELETE SET NULL;



ALTER TABLE entity_spec_ver
	ADD CONSTRAINT  R_240 FOREIGN KEY (id_entity_spec) REFERENCES entity_spec(id_entity_spec) ON DELETE SET NULL;



ALTER TABLE entity_spec_ver
	ADD CONSTRAINT  R_254 FOREIGN KEY (role_code) REFERENCES entity_role(role_code) ON DELETE SET NULL;



ALTER TABLE entity_spec_ver
	ADD CONSTRAINT  R_309 FOREIGN KEY (id_iter_cont) REFERENCES iterator_container(id_iter_cont) ON DELETE SET NULL;



ALTER TABLE entity_values
	ADD CONSTRAINT  R_37 FOREIGN KEY (id_update) REFERENCES model_data_update(id_update);



ALTER TABLE entity_values
	ADD CONSTRAINT  R_179 FOREIGN KEY (id_tuple) REFERENCES tuple(id_tuple);



ALTER TABLE entity_values
	ADD CONSTRAINT  R_216 FOREIGN KEY (id_entity_spec) REFERENCES entity_spec(id_entity_spec);



ALTER TABLE file_upload_info
	ADD CONSTRAINT  R_255 FOREIGN KEY (id_update) REFERENCES model_data_update(id_update) ON DELETE SET NULL;



ALTER TABLE iterator_atom
	ADD CONSTRAINT  R_295 FOREIGN KEY (id_set_spec) REFERENCES set_spec(id_set_spec) ON DELETE SET NULL;



ALTER TABLE iterator_atom
	ADD CONSTRAINT  R_317 FOREIGN KEY (id_iter_cont) REFERENCES iterator_container(id_iter_cont) ON DELETE SET NULL;



ALTER TABLE iterator_atom
	ADD CONSTRAINT  R_319 FOREIGN KEY (id_model) REFERENCES model(id_model) ON DELETE SET NULL;



ALTER TABLE iterator_container
	ADD CONSTRAINT  R_315 FOREIGN KEY (id_model) REFERENCES model(id_model) ON DELETE SET NULL;



ALTER TABLE iterator_in_container
	ADD CONSTRAINT  R_310 FOREIGN KEY (id_iter_cont) REFERENCES iterator_container(id_iter_cont);



ALTER TABLE iterator_in_container
	ADD CONSTRAINT  R_311 FOREIGN KEY (id_iter_atom) REFERENCES iterator_atom(id_iter_atom);



ALTER TABLE members_dictionary
	ADD CONSTRAINT  R_210 FOREIGN KEY (id_set) REFERENCES set_data(id_set);



ALTER TABLE model
	ADD CONSTRAINT  R_2 FOREIGN KEY (id_model_type) REFERENCES model_type(id_model_type) ON DELETE SET NULL;



ALTER TABLE model
	ADD CONSTRAINT  R_3 FOREIGN KEY (created_by) REFERENCES Usr(id_Usr) ON DELETE SET NULL;



ALTER TABLE model
	ADD CONSTRAINT  R_128 FOREIGN KEY (modified_by) REFERENCES Usr(id_Usr) ON DELETE SET NULL;



ALTER TABLE model
	ADD CONSTRAINT  R_294 FOREIGN KEY (id_model_template) REFERENCES model(id_model) ON DELETE SET NULL;



ALTER TABLE model_analysis
	ADD CONSTRAINT  R_281 FOREIGN KEY (id_instance) REFERENCES model_instance(id_instance) ON DELETE SET NULL;



ALTER TABLE model_analysis
	ADD CONSTRAINT  R_282 FOREIGN KEY (id_analysis_type) REFERENCES analysis_type(id_analysis_type) ON DELETE SET NULL;



ALTER TABLE model_analysis
	ADD CONSTRAINT  R_292 FOREIGN KEY (created_by) REFERENCES Usr(id_Usr);



ALTER TABLE model_data_update
	ADD CONSTRAINT  R_275 FOREIGN KEY (lck_sts) REFERENCES sn(id_sn) ON DELETE SET NULL;



ALTER TABLE model_data_update
	ADD CONSTRAINT  R_65 FOREIGN KEY (parent_id_update) REFERENCES model_data_update(id_update) ON DELETE SET NULL;



ALTER TABLE model_data_update
	ADD CONSTRAINT  R_116 FOREIGN KEY (updated_by) REFERENCES Usr(id_Usr) ON DELETE SET NULL;



ALTER TABLE model_data_update
	ADD CONSTRAINT  R_187 FOREIGN KEY (id_model) REFERENCES model(id_model) ON DELETE SET NULL;



ALTER TABLE model_data_update
	ADD CONSTRAINT  R_271 FOREIGN KEY (db_sts) REFERENCES db_sts(id_db_sts) ON DELETE SET NULL;



ALTER TABLE model_data_update
	ADD CONSTRAINT  R_273 FOREIGN KEY (sn) REFERENCES sn(id_sn) ON DELETE SET NULL;



ALTER TABLE model_identifiers
	ADD CONSTRAINT  R_235 FOREIGN KEY (id_model) REFERENCES model(id_model);



ALTER TABLE model_instance
	ADD CONSTRAINT  R_276 FOREIGN KEY (id_model) REFERENCES model(id_model) ON DELETE SET NULL;



ALTER TABLE model_instance
	ADD CONSTRAINT  R_277 FOREIGN KEY (id_spec_rev) REFERENCES model_spec_rev(id_spec_rev) ON DELETE SET NULL;



ALTER TABLE model_instance
	ADD CONSTRAINT  R_278 FOREIGN KEY (id_update) REFERENCES model_data_update(id_update) ON DELETE SET NULL;



ALTER TABLE model_instance
	ADD CONSTRAINT  R_279 FOREIGN KEY (id_state) REFERENCES instance_state(id_state) ON DELETE SET NULL;



ALTER TABLE model_instance
	ADD CONSTRAINT  R_291 FOREIGN KEY (created_by) REFERENCES Usr(id_Usr);



ALTER TABLE model_spec_rev
	ADD CONSTRAINT  R_229 FOREIGN KEY (id_spec_state) REFERENCES spec_state(id_spec_state) ON DELETE SET NULL;



ALTER TABLE model_spec_rev
	ADD CONSTRAINT  R_236 FOREIGN KEY (id_model) REFERENCES model(id_model) ON DELETE SET NULL;



ALTER TABLE model_spec_rev
	ADD CONSTRAINT  R_270 FOREIGN KEY (db_sts) REFERENCES db_sts(id_db_sts) ON DELETE SET NULL;



ALTER TABLE model_spec_rev
	ADD CONSTRAINT  R_272 FOREIGN KEY (sn) REFERENCES sn(id_sn) ON DELETE SET NULL;



ALTER TABLE model_spec_rev
	ADD CONSTRAINT  R_274 FOREIGN KEY (lck_sts) REFERENCES sn(id_sn) ON DELETE SET NULL;



ALTER TABLE model_spec_rev
	ADD CONSTRAINT  R_66 FOREIGN KEY (parent_id_spec_rev) REFERENCES model_spec_rev(id_spec_rev) ON DELETE SET NULL;



ALTER TABLE model_spec_rev
	ADD CONSTRAINT  R_115 FOREIGN KEY (created_by) REFERENCES Usr(id_Usr) ON DELETE SET NULL;



ALTER TABLE model_spec_rev
	ADD CONSTRAINT  R_122 FOREIGN KEY (commited_by) REFERENCES Usr(id_Usr) ON DELETE SET NULL;



ALTER TABLE model_spec_rev
	ADD CONSTRAINT  R_129 FOREIGN KEY (modified_by) REFERENCES Usr(id_Usr) ON DELETE SET NULL;



ALTER TABLE parameter_values_view
	ADD CONSTRAINT  R_266 FOREIGN KEY (id_update) REFERENCES model_data_update(id_update);



ALTER TABLE parameter_values_view
	ADD CONSTRAINT  R_267 FOREIGN KEY (id_entity_spec) REFERENCES entity_spec(id_entity_spec);



ALTER TABLE parameter_values_view
	ADD CONSTRAINT  R_268 FOREIGN KEY (id_tuple) REFERENCES tuple(id_tuple);



ALTER TABLE preference
	ADD CONSTRAINT  R_284 FOREIGN KEY (id_analysis) REFERENCES model_analysis(id_analysis);



ALTER TABLE preference
	ADD CONSTRAINT  R_285 FOREIGN KEY (id_pref_type) REFERENCES preference_type(id_pref_type) ON DELETE SET NULL;



ALTER TABLE preference
	ADD CONSTRAINT  R_286 FOREIGN KEY (id_entity_spec) REFERENCES entity_spec(id_entity_spec) ON DELETE SET NULL;



ALTER TABLE set_attr
	ADD CONSTRAINT  R_74 FOREIGN KEY (id_category) REFERENCES set_attr_category(id_category);



ALTER TABLE set_attr
	ADD CONSTRAINT  R_76 FOREIGN KEY (id_category,element_no) REFERENCES set_attr_category_element(id_category,element_no) ON DELETE SET NULL;



ALTER TABLE set_attr
	ADD CONSTRAINT  R_194 FOREIGN KEY (id_set_rev) REFERENCES set_spec_ver(id_set_rev);



ALTER TABLE set_attr_category_element
	ADD CONSTRAINT  R_73 FOREIGN KEY (id_category) REFERENCES set_attr_category(id_category);



ALTER TABLE set_data
	ADD CONSTRAINT  R_208 FOREIGN KEY (id_set_spec) REFERENCES set_spec(id_set_spec);



ALTER TABLE set_data
	ADD CONSTRAINT  R_171 FOREIGN KEY (id_tuple) REFERENCES tuple(id_tuple) ON DELETE SET NULL;



ALTER TABLE set_member
	ADD CONSTRAINT  R_211 FOREIGN KEY (id_set) REFERENCES set_data(id_set);



ALTER TABLE set_member
	ADD CONSTRAINT  R_212 FOREIGN KEY (id_update) REFERENCES model_data_update(id_update);



ALTER TABLE set_member
	ADD CONSTRAINT  R_214 FOREIGN KEY (id_member) REFERENCES members_dictionary(id_member);



ALTER TABLE set_member_view
	ADD CONSTRAINT  R_262 FOREIGN KEY (id_update) REFERENCES model_data_update(id_update);



ALTER TABLE set_spec
	ADD CONSTRAINT  R_20 FOREIGN KEY (parent_id_set) REFERENCES set_spec(id_set_spec) ON DELETE SET NULL;



ALTER TABLE set_spec
	ADD CONSTRAINT  R_207 FOREIGN KEY (id_model) REFERENCES model(id_model) ON DELETE SET NULL;



ALTER TABLE set_spec
	ADD CONSTRAINT  R_316 FOREIGN KEY (id_iter_cont) REFERENCES iterator_container(id_iter_cont) ON DELETE SET NULL;



ALTER TABLE set_spec_ver
	ADD CONSTRAINT  R_118 FOREIGN KEY (created_by) REFERENCES Usr(id_Usr) ON DELETE SET NULL;



ALTER TABLE set_spec_ver
	ADD CONSTRAINT  R_237 FOREIGN KEY (id_set_spec) REFERENCES set_spec(id_set_spec) ON DELETE SET NULL;



ALTER TABLE set_spec_ver
	ADD CONSTRAINT  R_238 FOREIGN KEY (id_spec_rev) REFERENCES model_spec_rev(id_spec_rev) ON DELETE SET NULL;



ALTER TABLE submodel
	ADD CONSTRAINT  R_219 FOREIGN KEY (id_model) REFERENCES model(id_model);



ALTER TABLE term
	ADD CONSTRAINT  R_217 FOREIGN KEY (id_entity_rev) REFERENCES entity_spec_ver(id_entity_rev);



ALTER TABLE term_entity
	ADD CONSTRAINT  R_252 FOREIGN KEY (id_entity_spec) REFERENCES entity_spec(id_entity_spec);



ALTER TABLE term_entity
	ADD CONSTRAINT  R_259 FOREIGN KEY (id_term) REFERENCES term(id_term);



ALTER TABLE term_entity
	ADD CONSTRAINT  R_312 FOREIGN KEY (id_iter_cont) REFERENCES iterator_container(id_iter_cont) ON DELETE SET NULL;



ALTER TABLE tuple
	ADD CONSTRAINT  R_318 FOREIGN KEY (id_iter_cont) REFERENCES iterator_container(id_iter_cont) ON DELETE SET NULL;



ALTER TABLE tuple_element
	ADD CONSTRAINT  R_169 FOREIGN KEY (id_tuple) REFERENCES tuple(id_tuple);



ALTER TABLE tuple_element
	ADD CONSTRAINT  R_215 FOREIGN KEY (id_member) REFERENCES members_dictionary(id_member);

ALTER TABLE unit_dic
	ADD CONSTRAINT  R_321 FOREIGN KEY (id_model) REFERENCES model(id_model) ON DELETE SET NULL;

ALTER TABLE uploaded_entity
	ADD CONSTRAINT  R_245 FOREIGN KEY (id_file_upload) REFERENCES file_upload_info(id_file_upload) ON DELETE SET NULL;



ALTER TABLE uploaded_entity
	ADD CONSTRAINT  R_246 FOREIGN KEY (id_entity_spec) REFERENCES entity_spec(id_entity_spec) ON DELETE SET NULL;



ALTER TABLE uploaded_set
	ADD CONSTRAINT  R_244 FOREIGN KEY (id_file_upload) REFERENCES file_upload_info(id_file_upload) ON DELETE SET NULL;



ALTER TABLE uploaded_set
	ADD CONSTRAINT  R_248 FOREIGN KEY (id_set_spec) REFERENCES set_spec(id_set_spec) ON DELETE SET NULL;


ALTER TABLE entity_spec_ver
	ADD CONSTRAINT  R_320 FOREIGN KEY (id_unit) REFERENCES unit_dic(id_unit) ON DELETE SET NULL;
ALTER TABLE set_attr_category
	ADD CONSTRAINT  R_321 FOREIGN KEY (id_model) REFERENCES model(id_model) ON DELETE SET NULL;
ALTER TABLE entity_attr_category
	ADD CONSTRAINT  R_321 FOREIGN KEY (id_model) REFERENCES model(id_model) ON DELETE SET NULL;	