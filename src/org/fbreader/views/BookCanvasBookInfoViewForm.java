/*
 Copyright 2013 Sole Proprietorship Vita Tolstikova
 
 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

    
*  
* support website: http://software.avt.dn.ua
*
* support email address: support@software.avt.dn.ua
*  
*/

package org.fbreader.views;

import javax.microedition.lcdui.*;
import org.fbreader.localization.L10n;
import org.fbreader.Main;
import org.fbreader.models.BookInMyLibRMS;
import org.fbreader.utils.mylib.MyLibUtils;


/**
 * Class for displaying on the form parameters of the selected book
 *   
 */

public class BookCanvasBookInfoViewForm 
       extends Form
       implements View, CommandListener {
    
   private ViewMaster viewMaster;
   private MyLibUtils myLibUtils;
   private static BookInMyLibRMS selectedBookForRead;
   
    
    public BookCanvasBookInfoViewForm() {
        super(L10n.getMessage("MY_LIB_BOOK_INFO_FORM_TITLE"));
        viewMaster = ViewMaster.getInstance();
        myLibUtils=MyLibUtils.getInstance();
        this.addCommand(viewMaster.getBackCmd());
        setCommandListener(this);
        //show info about selected book
        showSelectedBookInfo();
    }
    
    /**
     * Activate BookCanvasBookInfoViewForm 
     * 
     */
    public void activate() {
    }
    
    
    /**
     * Deactivate BookCanvasBookInfoViewForm
     */
    public void deactivate() {
    }
    
    
     
    /**
     * show info about selected book
    */
    private void showSelectedBookInfo() {
            //Get selected book info for read
            try {
                selectedBookForRead=myLibUtils.getSelectedBookForRead();
            }
            catch (Exception e) {
                //#debug
                Main.LOGGER.logError("MyLibBookInfoViewForm::activate()"+e.getMessage());
            }

//--- 1 --- Book Title
        StringItem stringItemBookTitle = 
                new StringItem(L10n.getMessage("BOOK_TITLE_STRING_ITEM_TITLE"),
                              selectedBookForRead.getBookTitle().toLowerCase());
        this.append(stringItemBookTitle);
        
//--- 2 --- Book Author
        StringItem stringItemBookAuthor = 
                new StringItem(L10n.getMessage("BOOK_AUTHOR_STRING_ITEM_TITLE"),
                              selectedBookForRead.getBookAuthor().toLowerCase());
        this.append(stringItemBookAuthor);         
        
//--- 3 --- Book Tags
        StringItem stringItemBookTags = 
                new StringItem(L10n.getMessage("BOOK_TAGS_STRING_ITEM_TITLE"),
                              selectedBookForRead.getBookTags().toLowerCase());
        this.append(stringItemBookTags);         

//--- 4 --- Book Language
        StringItem stringItemBookLang = 
                new StringItem(L10n.getMessage("BOOK_LANG_STRING_ITEM_TITLE"),
                              selectedBookForRead.getBookLang().toLowerCase());
        this.append(stringItemBookLang);
    }
    
    
    /**
     * Handles the select command related to a Displayable
     * @param command
     * @param displayable The Displayable on which this event has occurred
     */
      public void commandAction(Command command, Displayable displayable) {
        
          if (command == viewMaster.getBackCmd()) {
            Main.getInstance().switchDisplayable(null, Main.getInstance().getbookCanvas());
          }
      }

}
