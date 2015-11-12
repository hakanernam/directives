package com.he

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional


class DirectiveController {
      static scaffold = Directive


      /**
    *
    * @return
    */
  def listLive() {
      params.max = Math.min(params.max ? params.max.toInteger() : 500, 1000)
      params.sort = params.sort ?: "text"

      //TODO: delegate this to DB. Set of locations.
      //def dlist = Directive.findAllByStateNotInList([DirectiveStatusType.CLOSED, DirectiveStatusType.STORED, DirectiveStatusType.PREACTIVE]);

      def dq = Directive.where{
           hidden == false
      }

      def dlist = dq.list(params);



      // TODO: Verify collect
      def dirLocs = dlist.collect{ it.location }.groupBy{ it.name }.keySet() //["GENEL","PERSONEL"]//

      println("Dlistsize: "+ dlist.size()+ " -- Dir locations="+ dirLocs)

      render dlist

  }
}
