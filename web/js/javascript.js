(function($) {

	$.fn.scrollPagination = function(options) {
		
		var settings = { 
			nop     : 3, // Количество запрашиваемых из БД записей
			offset  : 0, // Начальное смещение в количестве запрашиваемых данных
			error   : 'Барлық орталықтар көрсетілді!', // оповещение при отсутствии данных в БД
			delay   : 500, // Задержка перед загрузкой данных
			scroll  : true // Если true то записи будут подгружаться при прокрутке странице, иначе только при нажатии на кнопку 
		}
		
		// Включение опции для плагина
		if(options) {
			$.extend(settings, options);
		}
		
		return this.each(function() {		
			
			$this = $(this);
			$settings = settings;
			var offset = $settings.offset;
			var busy = false; // переменная для обозначения происходящего процесса
			
			// Текст кнопки, на основе параметров
			if($settings.scroll == true) $initmessage = 'Показать больше';
			else $initmessage = 'Кликни';
			
			$this.append('<div class="content"></div><div class="loading-bar">'+$initmessage+'</div>');
			
			// Функция AJAX запроса
			function getData() {
                $.ajax({
                    type: "POST",
                    url: "Control",
                    data: {
                        action: "scrollpagination",
                        number: $settings.nop,
                        offset: offset
                    },
                    dataType: "json",
                    success: function(data) {

                        $this.find('.loading-bar').html($initmessage);
                        if (data.success) {
                            offset = offset + $settings.nop;
                                var htmlContent = "";
                                var imageSrc = "";
                                for (var i = 0; i < data.centers.length; i++) {
                                    var r = data.centers[i].rating / data.centers[i].vote;
                                    var rating = Math.floor(r);
                                    if (!data.centers[i].encodedImage || 0 === data.centers[i].encodedImage.length) {
                                        imageSrc = "src='images/no-image120.png'";
                                    } else {
                                        imageSrc = "src='data:image/jpg;base64," + data.centers[i].encodedImage + "'";
                                    }
                                    htmlContent += "<div id='newsBlog' class='newsStyle'>" +
                                            "<p align='center' style='margin-top: 2px;'><a id='read_more' onclick='viewCenterCounter(" + data.centers[i].id + ");' href='controller?command=center&id_center=" + data.centers[i].id + "'><strong>" + data.centers[i].name + "</strong></a></p>" +
                                            "<img title='" + data.centers[i].name + "' width='123px' height='93px' style='float: left; padding-left: 5px; padding-right: 5px;' " + imageSrc + "/>" +
                                            "<p>Мекен-жай: " + data.centers[i].address + "</p>" +
                                            "<p style='margin-left: 5px;'>Қаралу саны: " + data.centers[i].view + "</p>" +
                                            "<p style='margin-left: 5px;'>Рейтинг: <img  src='images/"+rating+".png' /></p>" +
                                            "<p style='margin-left: 5px;'>Телефон: " + data.centers[i].phone + "</p>" +
                                            "</div>";
                                }

                            $this.find('.content').append(htmlContent);
                            // Процесс завершен	
                            busy = false;
                            
                        }
                        else {
                            $this.find('.loading-bar').html($settings.error);                            
                        }

                    }
                });
            }	
			
			getData(); // Запуск функции загрузки данных в первый раз
			
			// Если прокрутка включена
			if($settings.scroll == true) {
				// .. и пользователь прокручивает страницу
				$(window).scroll(function() {
					
					// Проверяем пользователя, находится ли он в нижней части страницы
					if($(window).scrollTop() + $(window).height() > $this.height() && !busy) {
						
						// Идет процесс
						busy = true;
						
						// Сообщить пользователю что идет загрузка данных
						$this.find('.loading-bar').html('Жүктелуде');
						
						// Запустить функцию для выборки данных с установленной задержкой
						// Это полезно, если у вас есть контент в футере
						setTimeout(function() {
							
							getData();
							
						}, $settings.delay);
							
					}	
				});
			}
			
			// кроме того конент может быть загружен нажатием на кнопку
			$this.find('.loading-bar').click(function() {
			
				if(busy == false) {
					busy = true;
					getData();
				}
			
			});
			
		});
	}

})(jQuery);
