function jumpWeek() {
  var jumpWeekInput = document.getElementById("jumpWeek").value;
  var year = jumpWeekInput.substring(0, 4);
  var week = jumpWeekInput.substring(6, 8);
  window.location.href = "/week?weekYear=" + week + "." + year;
}

function checkUnsavedData() {
  var hasChanges = false;
  for (var z = 0; z <= 6; z++) {
    var elementById = document.getElementById("days" + z + ".distance");
    if (elementById) {
      if (elementById.value !== elementById.defaultValue) {
        hasChanges = true;
        break;
      }
    }
  }
  if (hasChanges) {
    return confirm("You have unsaved data, continue ?");
  }
  return true;
}