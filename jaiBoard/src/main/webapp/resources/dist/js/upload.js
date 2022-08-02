function getFileInfo(fullName) {
    var fullname;
    var imgsrc;
    var getLink;

    var fileLink;

    if (checkImageType(fullName)) {
        imgsrc = "/file/display?fileName=" + fullName;

        fileLink = fullName.substr(14);

        var front = fullName.substr(0, 12);
        var end = fullName.substr(14);
        getLink = "/file/display?fileName=" + front + end;
    } else {
        imgsrc = "/resources/upload/files/file-icon.png";

        fileLink = fullName.substr(12);

        getLink = "/file/display?fileName=" + fullName;
    }

    fullname = fileLink.substr(fileLink.indexOf("_") + 1);

    return { fullname, imgsrc, getLink, fullName };
}

function checkImageType(fullname) {
    var pattern = /jpg$|gif$|png$|jpge$/i;

    return fullname.match(pattern);
}