<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="initial-scale=1, maximum-scale=1, user-scalable=no, width=device-width">
    <meta name="format-detection" content="telephone=no" />
    <meta http-equiv="Pragma" content="no-cache, max-age=0"/>
    <meta http-equiv="Expires" content="-1"/>
    <title>Apcoa Parking</title>
    <link rel="shortcut icon" href="../img/favicon.ico" type="image/x-icon; charset=binary" />
    <link rel="icon" href="../img/favicon.ico" type="image/x-icon; charset=binary" />
    <link rel="stylesheet" href="css/apcoa.min.css">
    <script src="js/apcoa.min.js"></script>

    <script src="//www.google.com/recaptcha/api/js/recaptcha_ajax.js"></script>
  </head>
  <body ng-app="apcoa" class="position--relative container">
    <!-- Start: Header -->
    <header class="header" ng-class="{'header--logged-in': User, 'csc--header': User.isAdmin}">
      <div ng-include="'templates/_header.html'"></div>
    </header>
    <!-- End: Header -->
    <!-- Start: Navigation -->
    <div ng-include="'templates/_navigation.html'"></div>
    <!-- End: Navigation -->
    <!-- Start: ui-view -->
    <div ui-view class="content"></div>
    <!-- End: ui-view -->
    <footer class="footer footer-user text--center" ng-if="User.isCustomer">
        <span>If you have any question, please contact customer service center: +46 1234-56789!</span>
    </footer>
    <footer class="footer text--center" ng-if="!User || !User.isCustomer"></footer>
  </body>
</html>