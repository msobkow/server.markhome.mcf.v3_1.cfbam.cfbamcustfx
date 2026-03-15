// Description: Java 25 Custom JavaFX Schema

/*
 *	Mark's Code Fractal 3.1 CFBam - Business Application Model
 *
 *	Copyright (c) 2016-2026 Mark Stephen Sobkow
 *
 *	These files are part of Mark's Code Fractal CFBam.
 *
 *	Mark's Code Fractal CFBam is available under dual commercial license from
 *	Mark Stephen Sobkow, or under the terms of the GNU General Public License,
 *	Version 3 or later with classpath and static linking exceptions.
 *
 *	As a special exception, Mark Sobkow gives you permission to link this library
 *	with independent modules to produce an executable, provided that none of them
 *	conflict with the intent of the GPLv3; that is, you are not allowed to invoke
 *	the methods of this library from non-GPLv3-compatibly licensed code. You may not
 *	implement an LPGLv3 "wedge" to try to bypass this restriction. That said, code which
 *	does not rely on this library is free to specify whatever license its authors decide
 *	to use. Mark Sobkow specifically rejects the infectious nature of the GPLv3, and
 *	considers the mere act of including GPLv3 modules in an executable to be perfectly
 *	reasonable given tools like modern Java's single-jar deployment options.
 *
 *	Mark's Code Fractal CFBam is free software: you can redistribute it and/or
 *	modify it under the terms of the GNU General Public License as published by
 *	the Free Software Foundation, either version 3 of the License, or
 *	(at your option) any later version.
 *
 *	Mark's Code Fractal CFBam is distributed in the hope that it will be useful,
 *	but WITHOUT ANY WARRANTY; without even the implied warranty of
 *	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *	GNU General Public License for more details.
 *
 *	You should have received a copy of the GNU General Public License
 *	along with Mark's Code Fractal CFBam.  If not, see <https://www.gnu.org/licenses/>.
 *
 *	If you wish to modify and use this code without publishing your changes,
 *	or integrate it with proprietary code, please contact Mark Stephen Sobkow
 *	for a commercial license at mark.sobkow@gmail.com
 */

package server.markhome.mcf.v3_1.cfbam.cfbamcustfx;

import java.io.Serializable;
import java.math.*;
import java.time.*;
import java.util.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.text.StringEscapeUtils;
import server.markhome.mcf.v3_1.cflib.*;
import server.markhome.mcf.v3_1.cflib.dbutil.*;
import server.markhome.mcf.v3_1.cflib.javafx.*;
import server.markhome.mcf.v3_1.cflib.xml.CFLibXmlUtil;
import server.markhome.mcf.v3_1.cfsec.cfsec.*;
import server.markhome.mcf.v3_1.cfint.cfint.*;
import server.markhome.mcf.v3_1.cfbam.cfbam.*;
import server.markhome.mcf.v3_1.cfsec.cfsecobj.*;
import server.markhome.mcf.v3_1.cfint.cfintobj.*;
import server.markhome.mcf.v3_1.cfbam.cfbamobj.*;
import server.markhome.mcf.v3_1.cfsec.cfsecjavafx.*;
import server.markhome.mcf.v3_1.cfint.cfintjavafx.*;
import server.markhome.mcf.v3_1.cfbam.cfbamjavafx.*;
import server.markhome.mcf.v3_1.cfsec.cfseccustfx.*;
import server.markhome.mcf.v3_1.cfint.cfintcustfx.*;

/**
 *	The CFBamCustSchema defines the interface object that is
 *	provided by the cust interface for manipulating the CFBam
 *	facet in the user interface.
 */
public class CFBamCustSchema
extends CFBamJavaFXSchema
implements ICFBamCustSchema
{
	public CFBamCustSchema() {
		super();
	}

	public ICFBamSchemaObj getCFBamSchema() {
		return( (ICFBamSchemaObj)schema );
	}

	protected ICFSecJavaFXSchema javafxSchema = null;

	public ICFSecJavaFXSchema getJavaFXSchema() {
		if( javafxSchema == null ) {
			javafxSchema = this;
		}
		return( javafxSchema );
	}

	public void setJavaFXSchema( ICFSecJavaFXSchema value ) {
		final String S_ProcName = "setJavaFXSchema";
		if( value == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				"value" );
		}
		javafxSchema = value;
	}

	@Override public ICFSecJavaFXSecGroupFactory getSecGroupFactory() {
		if( factorySecGroup == null ) {
			factorySecGroup = new CFSecCustSecGroupFactory( this );
		}
		return( factorySecGroup );
	}

	@Override public ICFSecJavaFXTSecGroupFactory getTSecGroupFactory() {
		if( factoryTSecGroup == null ) {
			factoryTSecGroup = new CFSecCustTSecGroupFactory( this );
		}
		return( factoryTSecGroup );
	}

	protected volatile static CFSecCustFacetPane S_singletonSecFacetPane = null;

	public CFSecCustFacetPane getSingletonSecFacetPane( ICFFormManager formManager ) {
		CFSecCustFacetPane pane = getSingletonSecFacetPane( formManager, this );
		return( pane );
	}

	public CFSecCustFacetPane getSingletonSecFacetPane( ICFFormManager formManager, ICFSecCustSchema argSchema ) {
		if( S_singletonSecFacetPane == null ) {
			S_singletonSecFacetPane = newSecFacetPane( formManager, argSchema );
		}
		return( S_singletonSecFacetPane );
	}

	public CFSecCustFacetPane newSecFacetPane( ICFFormManager formManager ) {
		CFSecCustFacetPane pane = newSecFacetPane( formManager, this );
		return( pane );
	}

	public CFSecCustFacetPane newSecFacetPane( ICFFormManager formManager, ICFSecCustSchema argSchema ) {
		CFSecCustFacetPane pane = new CFSecCustFacetPane( formManager, argSchema );
		return( pane );
	}

	public CFBorderPane newManageClusterSecGroupForm( ICFFormManager formManager ) {
		CFBorderPane pane = new CFSecCustManageClusterSecGroupForm( formManager, this );
		return( pane );
	}

	public CFBorderPane newManageClusterSecGroupForm( ICFFormManager formManager, ICFSecCustSchema argSchema ) {
		CFBorderPane pane = new CFSecCustManageClusterSecGroupForm( formManager, argSchema );
		return( pane );
	}

	public CFBorderPane newManageClusterSecGroupPane( ICFFormManager formManager ) {
		CFBorderPane pane = new CFSecCustManageClusterSecGroupPane( formManager, this );
		return( pane );
	}

	public CFBorderPane newManageClusterSecGroupPane( ICFFormManager formManager, ICFSecCustSchema argSchema ) {
		CFBorderPane pane = new CFSecCustManageClusterSecGroupPane( formManager, argSchema );
		return( pane );
	}

	public CFBorderPane newManageTenantTSecGroupForm( ICFFormManager formManager ) {
		CFBorderPane pane = new CFSecCustManageTenantTSecGroupForm( formManager, this );
		return( pane );
	}

	public CFBorderPane newManageTenantTSecGroupForm( ICFFormManager formManager, ICFSecCustSchema argSchema ) {
		CFBorderPane pane = new CFSecCustManageTenantTSecGroupForm( formManager, argSchema );
		return( pane );
	}

	public CFBorderPane newManageTenantTSecGroupPane( ICFFormManager formManager ) {
		CFBorderPane pane = new CFSecCustManageTenantTSecGroupPane( formManager, this );
		return( pane );
	}

	public CFBorderPane newManageTenantTSecGroupPane( ICFFormManager formManager, ICFSecCustSchema argSchema ) {
		CFBorderPane pane = new CFSecCustManageTenantTSecGroupPane( formManager, argSchema );
		return( pane );
	}

	public CFBamCustFacetPane newBamFacetPane( ICFFormManager formManager ) {
		CFBamCustFacetPane pane = newBamFacetPane( formManager, this );
		return( pane );
	}

	public CFBamCustFacetPane newBamFacetPane( ICFFormManager formManager, ICFBamCustSchema argSchema ) {
		CFBamCustFacetPane pane = new CFBamCustFacetPane( formManager, argSchema );
		return( pane );
	}

	public ICFIntSchemaObj getCFIntSchema() {
		return( (ICFIntSchemaObj)schema );
	}

	public CFIntCustFacetPane newIntFacetPane( ICFFormManager formManager ) {
		CFIntCustFacetPane pane = newIntFacetPane( formManager, this );
		return( pane );
	}

	public CFIntCustFacetPane newIntFacetPane( ICFFormManager formManager, ICFIntCustSchema argSchema ) {
		CFIntCustFacetPane pane = new CFIntCustFacetPane( formManager, argSchema );
		return( pane );
	}
}
