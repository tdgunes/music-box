$ ->
  $("#catalog").accordion()
  $("#catalog li").draggable
    appendTo: "body"
    helper: "clone"

  $("#cart ol").droppable(
    activeClass: "ui-state-default"
    hoverClass: "ui-state-hover"
    accept: ":not(.ui-sortable-helper)"
    drop: (event, ui) ->
      $(this).find(".placeholder").remove()
      $("<li></li>").text(ui.draggable.text()).appendTo this
      return
  ).sortable
    items: "li:not(.placeholder)"
    sort: ->
      
      # gets added unintentionally by droppable interacting with sortable
      # using connectWithSortable fixes this, but doesn't allow you to customize active/hoverClass options
      $(this).removeClass "ui-state-default"
      return

  return