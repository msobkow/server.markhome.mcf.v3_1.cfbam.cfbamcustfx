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

import javafx.geometry.Orientation;
import javafx.scene.layout.AnchorPane;

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

public class CFBamCustMainPane
extends AnchorPane
implements ICFSecAuthorizationCallbacks
{
	protected ICFBamCustSchema custSchema = null;
	protected CFSplitPane splitPane = null;
	protected CFTitledPane appConsoleTitledPane = null;
	protected CFConsole appConsole = null;
	protected CFTabPane tabPane = null;
	protected CFTabFormManager tabSec = null;
	protected CFTabFormManager tabInt = null;
	protected CFTabFormManager tabBam = null;
	protected CFSecCustFacetPane paneSecFacet = null;
	protected CFIntCustFacetPane paneIntFacet = null;
	protected CFBamCustFacetPane paneBamFacet = null;

	public CFBamCustMainPane(
		ICFBamCustSchema argSchema )
	{
		super();
		custSchema = argSchema;
		setMinHeight( 480 );
		setMinWidth( 800 );
		splitPane = new CFSplitPane();

		appConsoleTitledPane = new CFTitledPane();
		appConsoleTitledPane.setText( "Console Log" );

		appConsole = new CFConsole();
		appConsole.setMinHeight( 60 );
		appConsoleTitledPane.setContent( appConsole );

		tabPane = new CFTabPane();

		tabSec = new CFTabFormManager() {
			public void showRootMainForm() {
				if( paneSecFacet == null ) {
					paneSecFacet = new CFSecCustFacetPane( tabSec, argSchema );
				}
				tabSec.setRootForm( paneSecFacet );
			}
		};
		tabSec.setText( "Sec" );
		tabSec.setClosable( false );
		tabSec.setContent( paneSecFacet );
		tabPane.getTabs().add( tabSec );

		if( paneSecFacet == null ) {
			paneSecFacet = new CFSecCustFacetPane( tabSec, argSchema );
		}
		paneSecFacet.setAuthorizationCallbacks( this );

		tabInt = new CFTabFormManager() {
			public void showRootMainForm() {
				if( paneSecFacet == null ) {
					paneIntFacet = new CFIntCustFacetPane( tabSec, argSchema );
				}
				tabInt.setRootForm( paneIntFacet );
			}
		};
		tabInt.setText( "Int" );
		tabInt.setClosable( false );
		tabInt.setContent( paneIntFacet );
		tabInt.setDisable( true );
		tabPane.getTabs().add( tabInt );

		if( paneIntFacet == null ) {
			paneIntFacet = new CFIntCustFacetPane( tabInt, argSchema );
		}

		tabBam = new CFTabFormManager() {
			public void showRootMainForm() {
				if( paneBamFacet == null ) {
					paneBamFacet = new CFBamCustFacetPane( tabBam, argSchema );
				}
				tabBam.setRootForm( paneBamFacet );
			}
		};

		tabBam.setText( "Bam" );
		tabBam.setClosable( false );
		tabBam.setContent( paneBamFacet );
		tabBam.setDisable( true );
		tabPane.getTabs().add( tabBam );
		paneBamFacet = new CFBamCustFacetPane( tabBam, argSchema );
		tabBam.setRootForm( paneBamFacet );
		splitPane.setOrientation( Orientation.VERTICAL );
		splitPane.getItems().add( tabPane );
		splitPane.getItems().add( appConsoleTitledPane );
		setTopAnchor( splitPane, 0.0 );
		setLeftAnchor( splitPane, 0.0 );
		setRightAnchor( splitPane, 0.0 );
		setBottomAnchor( splitPane, 0.0 );
		getChildren().addAll( splitPane );
		tabBam.setDisable( true );

		paneBamFacet.showFacet();
		paneIntFacet.showFacet();
		paneSecFacet.showLogin();
	}

	public ICFBamCustSchema getCustSchema() {
		return( custSchema );
	}

	public void setCustSchema( ICFBamCustSchema argSchema ) {
		final String S_ProcName = "setCustSchema";
		final String S_ArgNameSchema = "argSchema";
		if( argSchema == null ) {
			throw new CFLibNullArgumentException( getClass(),
				S_ProcName,
				1,
				S_ArgNameSchema );
		}
		if( ! ( argSchema instanceof ICFBamCustSchema ) ) {
			throw new CFLibUnsupportedClassException( getClass(),
				S_ProcName,
				S_ArgNameSchema,
				argSchema,
				"ICFBamCustSchema" );
		}
		custSchema = (ICFBamCustSchema)argSchema;
		if( paneSecFacet != null ) {
			paneSecFacet.setCustSchema( custSchema );
		}
		if( paneIntFacet != null ) {
			paneIntFacet.setCustSchema( custSchema );
		}
		if( paneBamFacet != null ) {
			paneBamFacet.setCustSchema( custSchema );
		}
	}

	public void loggedIn() {
		tabSec.setDisable( false );
		tabInt.setDisable( false );
		tabBam.setDisable( false );
		paneIntFacet.showFacet();
		paneBamFacet.showFacet();
	}

	public void preLogout() {
		tabSec.setDisable( false );
		tabInt.setDisable( true );
		tabBam.setDisable( true );
		paneIntFacet.showFacet();
		paneBamFacet.showFacet();
	}
}
